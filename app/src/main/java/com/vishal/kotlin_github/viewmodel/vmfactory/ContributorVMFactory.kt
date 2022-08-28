package com.vishal.kotlin_github.viewmodel.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishal.kotlin_github.network.GithubInterface
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import com.vishal.kotlin_github.viewmodel.ContributorViewModel


class ContributorVMFactory(private val network: NetworkRepositoryImpl)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContributorViewModel::class.java)) {
            return ContributorViewModel(network) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }

}