package bg.asentt.simplegetrequestbyretrofitwithkotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//https://www.youtube.com/watch?v=pJ2yIosUYnE
//https://api.github.com/users
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = Utils.createInstance().create(ApiInterface::class.java)
        Log.d("TAG", "TEST")
        println("TEST")

        Log.d("TAG", "before LAUNCH")
        GlobalScope
            .launch {
            val results = user.getUsers()
            if (results.body() !=null) {
                Log.d("TAG", "onCreate: ${results.body().toString()}")
            } else {
                Log.d("TAG", "onCreate: ${results.body().toString()}")
            }
        }
    }
}