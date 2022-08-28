package com.vishal.kotlin_github.viewmodel.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishal.kotlin_github.network.GithubInterface
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import com.vishal.kotlin_github.viewmodel.ProfileViewModel


class ProfileVMFactory constructor(private val network: NetworkRepositoryImpl)
        : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(network) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }

}