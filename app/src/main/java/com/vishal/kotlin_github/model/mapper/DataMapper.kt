package com.vishal.kotlin_github.model.mapper

import com.vishal.kotlin_github.model.*
import com.vishal.kotlin_github.util.MapperUtils

object DataMapper {

    fun mapUserData(response: User?): UserModel? {
        if(response != null){
            return UserModel(
                avatarUrl = response.avatarUrl,
                bio = response.bio,
                followers = response.followers,
                following = response.following,
                publicRepos = response.publicRepos,
                login = response.login,
                name = response.name
            )
        }
        return null
    }

    fun mappedRepositoryList(response: List<RepositoryItem>?): List<RepositoryItemModel> {
        if(response != null && response.isNotEmpty()){
            return response.map{ MapperUtils.mapRepositoryData(it) }
        }
        return emptyList<RepositoryItemModel>()
    }

    fun mappedContributorList(response: List<ContributorsItem>?): List<ContributorsItemModel> {
        if(response != null && response.isNotEmpty()){
            return response.map{ MapperUtils.mapContributorData(it) }
        }
        return emptyList<ContributorsItemModel>()
    }

}