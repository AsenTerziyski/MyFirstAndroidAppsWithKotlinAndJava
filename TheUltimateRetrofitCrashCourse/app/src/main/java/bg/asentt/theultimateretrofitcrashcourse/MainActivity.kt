package bg.asentt.theultimateretrofitcrashcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import bg.asentt.theultimateretrofitcrashcourse.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception


//https://www.youtube.com/watch?v=t6Sql3WMAnk
//https://jsonplaceholder.typicode.com/todos

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope
            .launchWhenCreated {

                binding.progressBar.isVisible = true

                val response = try {
                    RetrofitInstanceSingleton.api.getTodos()
                } catch (e: IOException) {
                    Log.e(TAG, "This is IOException")
                    binding.progressBar.isVisible = false
                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.e(TAG, "HttpException, unexpected response")
                    binding.progressBar.isVisible = false
                    return@launchWhenCreated
                }

                if (response.isSuccessful && response.body() != null) {
                    todoAdapter.todos = response.body()!!
                } else {
                    Log.e(TAG, "Response not successful")
                }

                delay(5000L)
                binding.progressBar.isVisible = false

            }

    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}