package com.vishal.kotlin_github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.kotlin_github.network.GithubInterface
import com.vishal.kotlin_github.model.RepositoryItem
import com.vishal.kotlin_github.model.RepositoryItemModel
import com.vishal.kotlin_github.model.User
import com.vishal.kotlin_github.model.UserModel
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import kotlinx.coroutines.launch


class ProfileViewModel(private val network: NetworkRepositoryImpl): ViewModel() {
    lateinit var username: String

    private val _userResponse = MutableLiveData<UserModel?>()
    val userResponse: MutableLiveData<UserModel?> get() = _userResponse

    private val _userLoading = MutableLiveData<Boolean>()
    val userLoading: LiveData<Boolean> get() = _userLoading

    private val _userFailure = MutableLiveData<Boolean>()
    val userFailure: LiveData<Boolean> get() = _userFailure

    private val _repoResponse = MutableLiveData<List<RepositoryItemModel>>()
    val repoResponse: LiveData<List<RepositoryItemModel>> get() = _repoResponse

    private val _repoLoading = MutableLiveData<Boolean>()
    val repoLoading: LiveData<Boolean> get() = _repoLoading

    private val _repoFailure = MutableLiveData<Boolean>()
    val repoFailure: LiveData<Boolean> get() = _repoFailure

    init{
        _userLoading.value = true
        _userFailure.value = false
        _repoResponse.value = emptyList()
        _repoLoading.value = true
        _repoFailure.value = false
    }

    //USER API CALLED
    fun userApiCall() = viewModelScope.launch {
        val userData = network.getUserObject(username)

        if(userData != null){
            _userLoading.value = false
            _userResponse.postValue(userData)
        } else {
            _userLoading.value = false
            _userFailure.value = true
        }
    }

    //REPOSITORY API CALLED
    fun repoApiCall() = viewModelScope.launch {
        val repositoryList = network.getRepositoryList(username)

        if(repositoryList.isNotEmpty()){
            _repoLoading.value = false
            _repoResponse.postValue(repositoryList)
        } else {
            _repoLoading.value = false
            _repoFailure.value = true
        }
    }

}
