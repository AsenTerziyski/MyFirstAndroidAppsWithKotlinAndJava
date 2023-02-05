package com.example.mychucknoris.datasources

import com.example.mychucknoris.model.ChuckInfoData
import com.example.mychucknoris.chuckapi.ApiClient
import com.example.mychucknoris.model.ChuckInfoDataSearch
import retrofit2.Response

class DataRemoteSource(private val apiClient: ApiClient) {

    suspend fun getCategories(): Response<List<String>> {
        return apiClient.getCategories()
    }

    suspend fun getRandomJoke(): Response<ChuckInfoData> {
        return apiClient.getRandomJoke()
    }

    suspend fun getRandomJokeByCategory(cat:String) : Response<ChuckInfoData> {
        return apiClient.getRandomJokeByCategory(cat)
    }

    suspend fun getJokeBySearchText(searchText: String) : Response<ChuckInfoDataSearch> {
        return apiClient.getJokeBySearchText(searchText)
    }

}