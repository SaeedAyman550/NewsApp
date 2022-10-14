package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.localDataBase.ArticleDao
import com.example.newsapp.data.localDataBase.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {


    @Singleton
    @Provides
    fun provideDataBase(context: Application): ArticleDataBase =
        Room.databaseBuilder(context, ArticleDataBase::class.java, "article_dataBase")
            .build()



    @Singleton
    @Provides
    fun provideArticleDao(articleDataBase: ArticleDataBase):ArticleDao= articleDataBase.articleDao()






}