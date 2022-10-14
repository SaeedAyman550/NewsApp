package com.example.newsapp.di

import com.example.newsapp.data.localDataBase.ArticleDao
import com.example.newsapp.data.network.NewsApi
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.models.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepoModule {


    @Provides
    @Singleton
    fun getRepo(articleDao: ArticleDao, api: NewsApi): Repository =NewsRepository(articleDao,api)

}