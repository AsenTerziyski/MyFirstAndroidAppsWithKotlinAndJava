package com.example.mychucknoris

import com.example.mychucknoris.model.ChuckCategories
import com.example.mychucknoris.model.ChuckInfoData
import com.example.mychucknoris.model.ChuckInfoDataSearch
import com.example.mychucknoris.datasources.DataLocalSource
import com.example.mychucknoris.datasources.DataRemoteSource
import retrofit2.Response

class Repository(
    private val localSource: DataLocalSource,
    private val remoteSource: DataRemoteSource
) {

    suspend fun getCategoriesFromRemote(): Response<List<String>> {
        return remoteSource.getCategories()
    }

    suspend fun getRandomJokeByCategory(cat: String): Response<ChuckInfoData> {
        return remoteSource.getRandomJokeByCategory(cat)
    }

    suspend fun getRandomJokeFromRemote(): Response<ChuckInfoData> {
        return remoteSource.getRandomJoke()
    }

    suspend fun getJokeBySearchText(searchText: String) :Response<ChuckInfoDataSearch> {
        return remoteSource.getJokeBySearchText(searchText)
    }

    fun getCategoriesFromLocal(): ChuckCategories {
        return localSource.getLocalCategories()
    }

}