package bg.asentt.simplegetrequestbyretrofitwithkotlincoroutines

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Utils {
    var BASE = "https://api.github.com/"
    fun createInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}