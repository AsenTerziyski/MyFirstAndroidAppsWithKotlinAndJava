package com.example.mychucknoris

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychucknoris.model.ChuckInfoData
import com.example.mychucknoris.model.ChuckInfoDataSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _liveDataJokeCategories = MutableLiveData<List<String>>()
    val liveDataJokeCategories: LiveData<List<String>> = _liveDataJokeCategories

    private val _liveDataRandomJoke = MutableLiveData<ChuckInfoData>()
    val liveDataRandomJoke: LiveData<ChuckInfoData> = _liveDataRandomJoke

    private val _liveDataRandomJokeByCategory = MutableLiveData<ChuckInfoData>()
    val liveDataRandomJokeByCategory: LiveData<ChuckInfoData> = _liveDataRandomJokeByCategory

    private val _liveDataJokeBySearchText = MutableLiveData<ChuckInfoDataSearch>()
    val liveDataJokeBySearchText: LiveData<ChuckInfoDataSearch> = _liveDataJokeBySearchText

    var job: Job? = null

    fun requestCategories() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getCategoriesFromRemote()
            if (result.isSuccessful) {
                Log.d("MyTAG", "requestCategories SUCCESS -> ${result.body()}")
                _liveDataJokeCategories.postValue(result.body())
            } else {
                Log.d("MyTAG", "requestCategories FAILURE -> ${result.message()}")
            }
        }
    }

    fun requestRandomJoke() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getRandomJokeFromRemote()
            if (result.isSuccessful) {
                Log.d("MyTAG", "requestRandomJoke SUCCESS -> ${result.body()}")
                _liveDataRandomJoke.postValue(result.body())
            } else {
                Log.d("MyTAG", "requestRandomJoke FAILURE -> ${result.message()}")
            }
        }
    }

    fun requestRandomJokeByCategory(cat: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getRandomJokeByCategory(cat)
            if (result.isSuccessful) {
                Log.d("MyTAG", "requestRandomJokeByCategory SUCCESS -> ${result.body()}")
                _liveDataRandomJokeByCategory.postValue(result.body())
            } else {
                Log.d("MyTAG", "requestRandomJokeByCategory FAILURE -> ${result.message()}")
            }
        }
    }

    fun requestJokeBySearchText(searchText: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getJokeBySearchText(searchText)
            if (result.isSuccessful) {
                Log.d("MyTAG", "requestJokeBySearchText SUCCESS -> ${result.body()}")
                _liveDataJokeBySearchText.postValue(result.body())
            } else {
                Log.d("MyTAG", "requestJokeBySearchText FAILURE -> ${result.message()}")
            }
        }
    }


}