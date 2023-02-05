package com.example.mychucknoris.chuckapi

import com.example.mychucknoris.model.ChuckInfoData
import com.example.mychucknoris.model.ChuckInfoDataSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("jokes/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("jokes/random")
    suspend fun getRandomJoke(): Response<ChuckInfoData>

    @GET("jokes/random")
    suspend fun getRandomJokeByCategory(@Query("category") category: String): Response<ChuckInfoData>

    @GET("jokes/search")
    suspend fun getJokeBySearchText(@Query("query") query: String): Response<ChuckInfoDataSearch>
}