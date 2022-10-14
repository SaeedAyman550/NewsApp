package com.example.newsapp.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Repository
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.util.Resourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: Repository):ViewModel() {


    private val _searchNewsLiveData: MutableLiveData<Resourse<NewsResponse>> = MutableLiveData()
     val searchNewsLiveData: LiveData<Resourse<NewsResponse>> =_searchNewsLiveData


    private var searchNewsPage = 1
    private var searchNewsResponse: NewsResponse? = null
    private var newSearchQuery:String? = null



    fun searchNews(searchQuery: String) = viewModelScope.launch {
        safeSearchNewsCall(searchQuery)
    }

    private suspend fun safeSearchNewsCall(searchQuery: String) {
        newSearchQuery = searchQuery
        _searchNewsLiveData.postValue(Resourse.Loading())
        try {

                val response = repo.searchForNews(searchQuery, searchNewsPage)
                _searchNewsLiveData.postValue(handleSearchNewsResponse(response))

        } catch(t: Throwable) {
            _searchNewsLiveData.postValue(t.message?.let { Resourse.Error(it) })

        }
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resourse<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                searchNewsPage++
                if(searchNewsResponse == null) {
                    searchNewsResponse = resultResponse
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resourse.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resourse.Error(response.message())
    }






}