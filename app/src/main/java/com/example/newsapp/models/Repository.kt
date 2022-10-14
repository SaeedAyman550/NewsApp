package com.example.newsapp.models

import androidx.lifecycle.LiveData
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.util.Constants
import retrofit2.Response

interface Repository {



    //room
    suspend fun insertNews(article: Article):Long

    fun getSavedNews(): LiveData<MutableList<Article>>

    suspend fun deleteNews(article: Article):Int


    //retrofit

    suspend fun getAllNews(countryCode: String = "eg", pageNumber: Int = 1, apiKey: String = Constants.API_KEY): Response<NewsResponse>


    suspend fun searchForNews(searchQuery: String, pageNumber: Int = 1, apiKey: String = Constants.API_KEY): Response<NewsResponse>




}