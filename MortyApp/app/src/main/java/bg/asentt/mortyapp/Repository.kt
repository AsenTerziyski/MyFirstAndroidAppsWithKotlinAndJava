package bg.asentt.mortyapp

import bg.asentt.mortyapp.network.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getCharacters(page: String) = apiService.fetchCharacters(page)
}