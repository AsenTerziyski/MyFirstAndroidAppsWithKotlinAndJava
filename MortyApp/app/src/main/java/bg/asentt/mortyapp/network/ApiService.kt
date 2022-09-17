package bg.asentt.mortyapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //https://rickandmortyapi.com/api/character/?page=19
    @GET("character")
    fun fetchCharacters(@Query("page") page: String): Call<CharacterResponse>
}