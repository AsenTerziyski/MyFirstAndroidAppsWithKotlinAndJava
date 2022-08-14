package bg.asentt.coroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

//https://www.youtube.com/watch?v=uiPYcSSjNTI
//https://www.youtube.com/watch?v=kvfpuzSwVZ8
class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GlobalScope.launch {
//            delay(3000L)
//            Log.d(TAG, "Coroutine says hello 1 from ${Thread.currentThread().name}")
//        }
//
//        GlobalScope.launch {
//            delay(7000L)
//            Log.d(TAG, "Coroutine says hello 2 from ${Thread.currentThread().name}")
//        }
//
//        GlobalScope.launch {
//            delay(2000L)
//            Log.d(TAG, "Coroutine says hello 3 from ${Thread.currentThread().name}")
//        }

//        Log.d(TAG, "Hello from thread ${Thread.currentThread().name }")
//
//        val goToSecondActivityButton: Button = findViewById(R.id.button_gotToSecondActivity)
//        goToSecondActivityButton.setOnClickListener {
////            lifecycleScope
////                .launch {
////                    while (true) {
////                        delay(1000L)
////                        counter++
////                        Log.d(TAG, "Still running! -> counter = $counter")
////
////                    }
////                }
////            GlobalScope
////                .launch {
////                    delay(5000L)
////                    Intent(this@MainActivity, SecondActivity::class.java).also {
////                        startActivity(it)
////                        counter+=10
////                        Log.d(TAG, "SecondActivity -> counter = $counter")
//////                        finish()
////                    }
////                }
//        }

        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5) {
//                Log.d(TAG, "Coroutine is still working!")
//                delay(1000L)
//                Log.d(TAG, "Started long running calculation...")
//                for(i in 30..40) {
//                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
//                }
//                Log.d(TAG, "Ending long running calculation!")

//            }
            Log.d(TAG, "Started long running calculation...")
            withTimeout(3000L) {
                for (i in 30..40) {
                    if (isActive) {
                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
            }

            Log.d(TAG, "Ending long running calculation!")
        }
//        runBlocking {
////            job.join()
//            delay(2000)
//            job.cancel()
////            Log.d(TAG, "MainThread is continuing...!")
//            Log.d(TAG, "Canceled job!")
//
//        }
    }

    fun fib(n: Int): Long {
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fib(n - 1) + fib(n - 2)
        }
    }
}