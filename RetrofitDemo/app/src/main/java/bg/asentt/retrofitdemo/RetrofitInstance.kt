package bg.asentt.retrofitdemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private const val baseURL = "https://gorest.co.in/public-api/"

        fun getRetrofit(): Retrofit {
            val logging = HttpLoggingInterceptor()

            logging.level = (HttpLoggingInterceptor.Level.BODY)

            val aClient = OkHttpClient.Builder()

            aClient.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(aClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}