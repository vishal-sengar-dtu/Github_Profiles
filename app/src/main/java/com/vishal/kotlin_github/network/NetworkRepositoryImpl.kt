package com.vishal.kotlin_github.network

import com.vishal.kotlin_github.model.*
import com.vishal.kotlin_github.model.mapper.DataMapper

class NetworkRepositoryImpl : NetworkRepository {

    //RETROFIT BUILDER
    private val github = RetrofitService.getInstance(false)

    override suspend fun getUserObject(username: String): UserModel? {
        val response = github.getUserObject(username)
        return DataMapper.mapUserData(response.body())
    }

    override suspend fun getUserObject(): UserModel? {
        val response = github.getUserObject()
        return DataMapper.mapUserData(response.body())
    }

    override suspend fun getRepositoryList(username: String): List<RepositoryItemModel> {
        val response = github.getRepositoryList(username)
        return DataMapper.mappedRepositoryList(response.body())
    }

    override suspend fun getRepositoryList(): List<RepositoryItemModel> {
        val response = github.getRepositoryList()
        return DataMapper.mappedRepositoryList(response.body())
    }

    override suspend fun getContributorList(
        username: String,
        repository: String
    ): List<ContributorsItemModel> {
        val response = github.getContributorList(username, repository)
        return DataMapper.mappedContributorList(response.body())
    }

    override suspend fun getContributorList(): List<ContributorsItemModel> {
        val response = github.getContributorList()
        return DataMapper.mappedContributorList(response.body())
    }

}