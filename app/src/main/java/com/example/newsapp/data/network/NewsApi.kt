package com.example.newsapp.data.network

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country") countryCode: String = "eg",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ):Response<NewsResponse>


    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String =Constants.API_KEY
    ): Response<NewsResponse>
}

