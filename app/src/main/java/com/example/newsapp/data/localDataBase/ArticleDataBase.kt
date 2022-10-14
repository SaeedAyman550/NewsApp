package com.example.newsapp.data.localDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.Article

@TypeConverters(SourceConverter::class)
@Database(entities =[Article::class] , version = 6, exportSchema = false)
abstract class ArticleDataBase: RoomDatabase() {

   abstract fun articleDao():ArticleDao


}