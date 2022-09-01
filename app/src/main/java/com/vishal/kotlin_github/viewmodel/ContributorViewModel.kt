package com.vishal.kotlin_github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.kotlin_github.network.GithubInterface
import com.vishal.kotlin_github.model.ContributorsItem
import com.vishal.kotlin_github.model.ContributorsItemModel
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContributorViewModel(private val network: NetworkRepositoryImpl) : ViewModel(){
    lateinit var username: String
    lateinit var repository: String

    private val _response = MutableLiveData<List<ContributorsItemModel>>()
    val response: LiveData<List<ContributorsItemModel>> get() = _response

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _failure = MutableLiveData<Boolean>()
    val failure: LiveData<Boolean> get() = _failure

    init{
        _response.value = emptyList()
        _loading.value = true
        _failure.value = false
    }

    //CONTRIBUTOR API CALLED
    fun contributorApiCall() = viewModelScope.launch {
        val contributorList = network.getContributorList(username, repository)

        if(contributorList.isNotEmpty()){
            _loading.value = false
            _response.postValue(contributorList)
        } else {
            _loading.value = false
            _failure.value = true
        }
    }

}