package bg.asentt.mortyapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.asentt.mortyapp.network.ApiClient
import bg.asentt.mortyapp.network.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private val _characterLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData: LiveData<ScreenState<List<Character>?>> = _characterLiveData
//        get() = _characterLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        _characterLiveData.postValue(ScreenState.Loading(null))

        Log.d("TAG", "MainViewModel --> " + Thread.currentThread().name)
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", "MainViewModel --> " + Thread.currentThread().name)
            delay(5000)
            try {
                val client = repo.getCharacters("1")
                _characterLiveData.postValue(ScreenState.Success(client.result))
                Log.d("TAG", "Success MainViewModel ---> ${client.result}")
            } catch (e: Exception) {
                _characterLiveData.postValue(ScreenState.Error(e.message.toString(), null))
            }
        }

    }
}