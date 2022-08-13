package com.example.simplepostrequestbyretrofitwithkotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)

        val obj = RequestModel("aaraf1231", "pass1231")
        retrofit.requestLogin(obj).enqueue(
            object : Callback<ResponseClass> {
                override fun onResponse(
                    call: Call<ResponseClass>,
                    response: Response<ResponseClass>
                ) {
                    Log.d("TAG", "${response.body()?.message}")
                    Toast.makeText(this@MainActivity, "${response.body()?.message}", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<ResponseClass>, t: Throwable) {

                }
            }
        )

    }
}