package com.example.newsapp.ui.fragments.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Article
import com.example.newsapp.models.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SavedViewModel @Inject constructor(private val repo: Repository):ViewModel() {



    fun saveArticle(article: Article) = viewModelScope.launch {
        repo.insertNews(article)
    }


    fun getSavedNews() = repo.getSavedNews()



    fun deleteArticle(article: Article) = viewModelScope.launch {
        repo.deleteNews(article)
    }







}