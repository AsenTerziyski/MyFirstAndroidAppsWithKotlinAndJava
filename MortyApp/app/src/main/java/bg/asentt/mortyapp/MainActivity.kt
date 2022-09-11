package bg.asentt.mortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bg.asentt.mortyapp.network.ApiClient
import bg.asentt.mortyapp.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


// PART 1 -> https://www.youtube.com/watch?v=s1jqC70uO7Q
// PART 2 -> https://www.youtube.com/watch?v=eUQebUJLnXI&list=PLDP3jMsmhGQVSkNFQKqNKnGNYUTMuV6W7&index=1
// PART 3 -> https://www.youtube.com/watch?v=Rob14eJ-Apw
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.apiService.fetchCharacters("1")
        client.enqueue(object : Callback<CharacterResponse> {

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG", "response.isSuccessful -->> ${response.body()}")
                    val result = response.body()?.result
                    result?.let {
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.characterRv)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }

                } else {
                    Log.d("TAG", "response.isSuccessful -->> Something went wrong!")
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.d("TAG", "failure -->> ${t.message}")
            }

        })
    }
}