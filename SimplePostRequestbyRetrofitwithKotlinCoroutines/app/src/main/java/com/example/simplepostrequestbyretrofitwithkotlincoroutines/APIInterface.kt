package com.example.simplepostrequestbyretrofitwithkotlincoroutines

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface APIInterface {
    @POST("/api/v1/create")
    fun requestLogin(@Body requestModel: RequestModel): Call<ResponseClass>
}