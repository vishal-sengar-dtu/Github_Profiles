package com.vishal.kotlin_github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.kotlin_github.apimanager.GithubInterface
import com.vishal.kotlin_github.model.RepositoryItem
import com.vishal.kotlin_github.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileViewModel(private val github: GithubInterface): ViewModel() {
    lateinit var username: String

    private val _userResponse = MutableLiveData<User>()
    val userResponse: LiveData<User> get() = _userResponse

    private val _userLoading = MutableLiveData<Boolean>()
    val userLoading: LiveData<Boolean> get() = _userLoading

    private val _userFailure = MutableLiveData<Boolean>()
    val userFailure: LiveData<Boolean> get() = _userFailure

    private val _repoResponse = MutableLiveData<List<RepositoryItem>>()
    val repoResponse: LiveData<List<RepositoryItem>> get() = _repoResponse

    private val _repoLoading = MutableLiveData<Boolean>()
    val repoLoading: LiveData<Boolean> get() = _repoLoading

    private val _repoFailure = MutableLiveData<Boolean>()
    val repoFailure: LiveData<Boolean> get() = _repoFailure

    init{
        _userLoading.value = true
        _repoLoading.value = true
        _repoFailure.value = false
    }

    //USER API CALLED
    fun userApiCall() = viewModelScope.launch {
        val response = github.getUserObject(username)

        if(response.isSuccessful){
            _userLoading.value = false
            _userResponse.postValue(response.body())
        } else {
            _userLoading.value = false
            _userFailure.value = true
        }
    }

    //REPOSITORY API CALLED
    fun repoApiCall() = viewModelScope.launch {
        val response = github.getRepositoryList(username)

        if(response.isSuccessful){
            _repoLoading.value = false
            _repoResponse.postValue(response.body())
        } else {
            _repoLoading.value = false
            _repoFailure.value = true
        }
    }

}
