package com.example.newsapp.data.repository

import com.example.newsapp.data.localDataBase.ArticleDao
import com.example.newsapp.data.network.NewsApi
import com.example.newsapp.models.Article
import com.example.newsapp.models.Repository

class  NewsRepository  (articleDao:ArticleDao, api: NewsApi): Repository {


    private val articleDao:ArticleDao=articleDao

    private val api:NewsApi=api



    //room
   override suspend fun insertNews(article: Article):Long= articleDao.insertNews(article)

  override  fun getSavedNews()=articleDao.getSavedNews()


    override suspend fun deleteNews(article: Article):Int=articleDao.deleteNews(article)


    //retrofit

    override suspend fun getAllNews(
        countryCode: String ,
        pageNumber: Int
        , apiKey: String
    ) = api.getAllNews(countryCode, pageNumber)


     override suspend fun searchForNews(
        searchQuery: String,
        pageNumber: Int
        , apiKey: String
    )=api.searchForNews(searchQuery,pageNumber)





}