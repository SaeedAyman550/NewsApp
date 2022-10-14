package com.example.newsapp.ui.fragments.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Repository
import com.example.newsapp.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticleViewModel @Inject constructor(private var repo: Repository):ViewModel() {



    fun saveArticle(article: Article) = viewModelScope.launch {

       repo.insertNews(article)

    }


}