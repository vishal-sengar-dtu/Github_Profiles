package com.vishal.kotlin_github.model

data class UserModel(
    val avatarUrl: String?,
    val bio: String?,
    val followers: Int?,
    val following: Int?,
    val publicRepos: Int?,
    val login: String?,
    val name: String?,
)

data class RepositoryItemModel(
    val description: String?,
    val forks: Int?,
    val fullName: String?,
    val language: String?,
    val name: String?,
    val stargazersCount: Int?,
    val updatedAt: String?,
)

data class ContributorsItemModel(
    val avatarUrl: String?,
    val contributions: Int?,
    val login: String?,
)
