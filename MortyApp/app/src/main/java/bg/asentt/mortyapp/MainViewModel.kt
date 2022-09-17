package bg.asentt.mortyapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bg.asentt.mortyapp.network.ApiClient
import bg.asentt.mortyapp.network.Character
import bg.asentt.mortyapp.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repo: Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private val _characterLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData: LiveData<ScreenState<List<Character>?>>
    get() = _characterLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        val client = repo.getCharacters("1")

        _characterLiveData.postValue(ScreenState.Loading(null))

        client.enqueue(object : Callback<CharacterResponse> {

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _characterLiveData.postValue(ScreenState.Success(response.body()?.result))
                    Log.d("TAG", "Success MainViewModel ---> ${response.body()?.result}")
                } else {
                    _characterLiveData.postValue(ScreenState.Error(response.code().toString(), null))
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                _characterLiveData.postValue(ScreenState.Error(t.message.toString(), null))
                Log.d("TAG", "Failure MainViewModel ---> ${t.message}")
            }

        })

    }
}