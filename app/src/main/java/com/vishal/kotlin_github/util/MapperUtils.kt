package com.vishal.kotlin_github.util

import com.vishal.kotlin_github.model.*

object MapperUtils {

    fun mapRepositoryData(response: RepositoryItem): RepositoryItemModel {
        return RepositoryItemModel(
            description = response.description?: "",
            name = response.name,
            forks = response.forks,
            fullName = response.fullName,
            language = response.language?: "",
            updatedAt = response.updatedAt?: "",
            stargazersCount = response.stargazersCount
        )
    }

    fun mapContributorData(response: ContributorsItem): ContributorsItemModel {
        return ContributorsItemModel(
            avatarUrl = response.avatarUrl,
            contributions = response.contributions,
            login = response.login
        )
    }

}