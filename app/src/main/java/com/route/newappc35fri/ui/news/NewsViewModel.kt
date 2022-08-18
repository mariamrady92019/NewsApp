package com.route.newappc35fri.ui.news

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newappc35fri.Constants
import com.route.newappc35fri.api.ApiManager
import com.route.newappc35fri.model.ArticlesItem
import com.route.newappc35fri.model.NewsResponse
import com.route.newappc35fri.model.SourcesItem
import com.route.newappc35fri.model.SourcesResponse
import com.route.newappc35fri.ui.categories.Category
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val progressBarVisible = MutableLiveData<Boolean>(false)
    val newsList = MutableLiveData<List<ArticlesItem?>?>()
    val searchedNewsList =MutableLiveData<List<ArticlesItem?>?>()
 var log = 0;

     fun getNewsSources(category: Category) {
        Log.e("get new sources",category.id)
        progressBarVisible.value = true
        viewModelScope.launch {

            try {
                val result = ApiManager.getApis()
                    .getNewsSources(Constants.apiKey, category.id)
                progressBarVisible.value = false;
                sourcesLiveData.value = result.sources
                Log.e("response", result.sources.toString())
            }catch (e: Exception){
                progressBarVisible.value = false;
                Log.e("on failure", e.localizedMessage)
            }



        }
    }

    fun loadNews(source: SourcesItem) {

        viewModelScope.launch {
            try {
                progressBarVisible.value = true;
              val result=  ApiManager.getApis()
                    .getNews(Constants.apiKey, source.id ?: "")
                progressBarVisible.value = false;
                newsList.value = result.articles
            }catch (e:Exception){
                progressBarVisible.value = false;
            }

        }
    }

    fun searchByKeywordAndSource(keyWord:String , sourcesItem: SourcesItem){

        viewModelScope.launch {
            try {
              val result=  ApiManager.getApis().searchInNews(Constants.apiKey,keyWord,sourcesItem.id?:"")
                progressBarVisible.value = false
                searchedNewsList.value = result.articles

            }catch (e:Exception){
                progressBarVisible.value= false
            }
        }

    }

}