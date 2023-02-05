package com.example.mychucknoris.chuckapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChuckApi {
    private var chuckRetrofitService: ApiClient? = null
    private const val BASE_URL = "https://api.chucknorris.io/"
    private val interceptor = HttpLoggingInterceptor()

    fun getClient(): ApiClient {

        val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        if (chuckRetrofitService == null) {
            val retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            chuckRetrofitService = retrofit.create(ApiClient::class.java)
        }

        return chuckRetrofitService!!
    }

}