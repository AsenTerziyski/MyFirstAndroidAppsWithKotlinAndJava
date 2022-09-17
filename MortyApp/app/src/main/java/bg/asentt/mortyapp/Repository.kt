package bg.asentt.mortyapp

import bg.asentt.mortyapp.network.ApiService

class Repository(private val apiService: ApiService) {
    fun getCharacters(page: String) = apiService.fetchCharacters(page)
}