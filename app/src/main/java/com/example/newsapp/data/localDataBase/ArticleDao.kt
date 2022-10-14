package com.example.newsapp.data.localDataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.models.Article

@Dao
interface ArticleDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: Article):Long

    @Query("SELECT *FROM article_table")
     fun getSavedNews():LiveData<MutableList<Article>>

    @Delete
    suspend fun deleteNews(article: Article):Int

}
