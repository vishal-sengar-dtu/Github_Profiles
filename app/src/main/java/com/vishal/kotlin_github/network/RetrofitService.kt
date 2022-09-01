package com.vishal.kotlin_github.network

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
    private var retrofitService : GithubInterface? = null

    fun getInstance(mock: Boolean) : GithubInterface {
        val baseUrl = if (mock) "https://run.mocky.io/" else "https://api.github.com/"

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(GithubInterface::class.java)
        }
        Log.d("API", "returning retrofit client")

        return retrofitService!!
    }

}