package com.vishal.kotlin_github.viewmodel.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishal.kotlin_github.apimanager.GithubInterface
import com.vishal.kotlin_github.viewmodel.ContributorViewModel

class ContributorVMFactory(private val github: GithubInterface)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContributorViewModel::class.java)) {
            return ContributorViewModel(github) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }

}