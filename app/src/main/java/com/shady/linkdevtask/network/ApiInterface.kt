package com.shady.linkdevtask.network

import com.shady.linkdevtask.models.ArticlesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/articles")
    fun getArticles(
        @Query("source") source: String,
        @Query("apiKey") apiKey: String
    ): Single<ArticlesResponse>

}