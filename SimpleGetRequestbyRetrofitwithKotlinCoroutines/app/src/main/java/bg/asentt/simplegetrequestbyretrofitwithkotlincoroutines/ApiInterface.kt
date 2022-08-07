package bg.asentt.simplegetrequestbyretrofitwithkotlincoroutines

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    fun getUsers() : Response<User>
}