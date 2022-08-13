package bg.asentt.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//https://www.youtube.com/watch?v=kvfpuzSwVZ8
class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            delay(3000L)
            Log.d(TAG, "Coroutine says hello 1 from ${Thread.currentThread().name}")
        }

        GlobalScope.launch {
            delay(7000L)
            Log.d(TAG, "Coroutine says hello 2 from ${Thread.currentThread().name}")
        }

        GlobalScope.launch {
            delay(2000L)
            Log.d(TAG, "Coroutine says hello 3 from ${Thread.currentThread().name}")
        }

        Log.d(TAG, "Hello from thread ${Thread.currentThread().name }")
    }
}