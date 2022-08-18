package com.route.newappc35fri.api

import com.route.newappc35fri.model.NewsResponse
import com.route.newappc35fri.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("sources") source: String
    ): NewsResponse

    @GET("v2/everything")
    fun searchInNews(
        @Query("apiKey") apiKey: String,
        @Query("q") keyword: String,
        @Query("sources") source: String
    ): NewsResponse
}