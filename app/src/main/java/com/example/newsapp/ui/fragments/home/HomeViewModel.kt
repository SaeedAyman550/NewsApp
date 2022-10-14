package com.example.newsapp.ui.fragments.home

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
class HomeViewModel @Inject constructor(private var repo: Repository):ViewModel() {



    private val _homeNewsLiveData: MutableLiveData<Resourse<NewsResponse>> = MutableLiveData()
     val homeNewsLiveData: LiveData<Resourse<NewsResponse>> = _homeNewsLiveData

    var homeNewsPage = 1
    var homeNewsResponse: NewsResponse? = null

    fun getAllNews(countryCode: String) = viewModelScope.launch {
        safeAllNewsCall(countryCode)
    }
    private fun handleAllNewsResponse(response: Response<NewsResponse>) : Resourse<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                homeNewsPage++
                if(homeNewsResponse == null) {
                    homeNewsResponse = resultResponse
                } else {
                    val oldArticles = homeNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resourse.Success(homeNewsResponse ?: resultResponse)
            }
        }

        return Resourse.Error(response.message())
    }
    private suspend fun safeAllNewsCall(countryCode: String) {
        _homeNewsLiveData.postValue(Resourse.Loading())
        try {


            val response = repo.getAllNews(countryCode, homeNewsPage)
            _homeNewsLiveData.postValue(handleAllNewsResponse(response))


        } catch(t: Throwable) {
            _homeNewsLiveData.postValue(t.message?.let {
                Resourse.Error(it) })

        }
    }











}