package bg.asentt.retrofitdemo

import android.icu.text.StringSearch
import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    // https://gorest.co.in/public-api/users
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUsersList(): Call<UsersList>

    //https://gorest.co.in/public-api/users?name=a
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query(value = "name") searchText: String): Call<UsersList>

    //https://gorest.co.in/public-api/users/100
    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUserById(@Path(value = "users_id") user_id: String?): Call<UserResponse>

    @POST("users")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer "
    )
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{users_id}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer "
    )
    fun updateUser(@Path("users/{users_id}") user_id: String, @Body params: User) :Call<UserResponse>


    @DELETE("users/{users_id}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer "
    )
    fun deleteUser(@Path("users/{users_id}") user_id: String) :Call<UserResponse>

}