package bg.asentt.simplegetrequestbyretrofitwithkotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = Utils.createInstance().create(ApiInterface::class.java)
        val results = user.getUsers()
        if (results != null) {
            Log.d("TAG", "On CREATE: ${results.body().toString()}")
        }
    }
}