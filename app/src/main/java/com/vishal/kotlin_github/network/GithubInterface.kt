package com.vishal.kotlin_github.network

import com.vishal.kotlin_github.model.ContributorsItem
import com.vishal.kotlin_github.model.RepositoryItem
import com.vishal.kotlin_github.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubInterface {

    @GET("users/{user}")
    suspend fun getUserObject(@Path("user") user : String) : Response<User>

    @GET("users/{user}/repos")
    suspend fun getRepositoryList(@Path("user") user : String) : Response<List<RepositoryItem>>

    @GET("repos/{user}/{repo}/contributors")
    suspend fun getContributorList(
        @Path("user") user : String,
        @Path("repo") repo : String
    ) : Response<List<ContributorsItem>>

    //MOCK CONTRACTS
    @GET("v3/2f19739c-f5ef-4516-8845-ee11f6d1c07a")
    suspend fun getUserObject() : Response<User>

    @GET("v3/e689a0cf-0387-434d-910b-27c3172922c7")
    suspend fun getRepositoryList() : Response<List<RepositoryItem>>

    @GET("v3/fbe6ec73-23b2-408b-a07f-6d43df78bd96")
    suspend fun getContributorList() : Response<List<ContributorsItem>>

}