package com.vishal.kotlin_github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.kotlin_github.apimanager.GithubInterface
import com.vishal.kotlin_github.model.ContributorsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContributorViewModel(private val github: GithubInterface) : ViewModel(){
    lateinit var username: String
    lateinit var repository: String

    private val _response = MutableLiveData<List<ContributorsItem>>()
    val response: LiveData<List<ContributorsItem>> get() = _response

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _failure = MutableLiveData<Boolean>()
    val failure: LiveData<Boolean> get() = _failure

    init{
        _loading.value = true
        _failure.value = false
        CoroutineScope(Dispatchers.IO).launch { contributorApiCall() }
    }

    //CONTRIBUTOR API CALLED
    private suspend fun contributorApiCall() = viewModelScope.launch {
        val response = github.getContributorList(username, repository)

        if(response.isSuccessful){
            _loading.value = false
            _response.postValue(response.body())
        } else {
            _loading.value = false
            _failure.value = true
        }
    }

}