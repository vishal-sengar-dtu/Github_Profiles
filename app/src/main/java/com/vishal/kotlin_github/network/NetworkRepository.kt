package com.vishal.kotlin_github.network

import com.vishal.kotlin_github.model.*

interface NetworkRepository {

    suspend fun getUserObject(): UserModel?;
    suspend fun getUserObject(username: String): UserModel?;

    suspend fun getRepositoryList(): List<RepositoryItemModel>
    suspend fun getRepositoryList(username: String): List<RepositoryItemModel>

    suspend fun getContributorList(): List<ContributorsItemModel>
    suspend fun getContributorList(username: String, repository: String): List<ContributorsItemModel>

}