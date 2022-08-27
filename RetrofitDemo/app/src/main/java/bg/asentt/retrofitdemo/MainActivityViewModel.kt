package bg.asentt.retrofitdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    var recyclerListData: MutableLiveData<UsersList> = MutableLiveData()

    fun getUserListObserver(): MutableLiveData<UsersList> {
        return recyclerListData
    }

    fun getUserList() {
        val retroInstance = RetrofitInstance.getRetrofit().create(RetroService::class.java)

        val call = retroInstance.getUsersList()

        call
            .enqueue(object : Callback<UsersList> {

                override fun onResponse(call: Call<UsersList>, response: Response<UsersList>) {
                    if (response.isSuccessful) {
                        recyclerListData.postValue(response.body())
                    } else {
                        recyclerListData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<UsersList>, t: Throwable) {
                    recyclerListData.postValue(null)
                }

            })
    }

    fun searchUser(searchText: String) {

        val retroInstance = RetrofitInstance.getRetrofit().create(RetroService::class.java)

        val call = retroInstance.searchUsers(searchText)

        call.enqueue(object : Callback<UsersList> {
            override fun onResponse(call: Call<UsersList>, response: Response<UsersList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UsersList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

        })
    }

}