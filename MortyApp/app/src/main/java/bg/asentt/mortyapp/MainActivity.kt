package bg.asentt.mortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bg.asentt.mortyapp.network.Character
import com.google.android.material.snackbar.Snackbar

// https://rickandmortyapi.com/

// PART 1 -> https://www.youtube.com/watch?v=s1jqC70uO7Q
// PART 2 -> https://www.youtube.com/watch?v=eUQebUJLnXI&list=PLDP3jMsmhGQVSkNFQKqNKnGNYUTMuV6W7&index=1
// PART 3 -> https://www.youtube.com/watch?v=Rob14eJ-Apw
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCharacters()
    }

    private fun getCharacters() {
        viewModel.characterLiveData.observe(this) { state ->
            processCharacterResponse(state)
        }
    }

    private fun processCharacterResponse(state: ScreenState<List<Character>?>) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        when(state) {
            is ScreenState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                progressBar.visibility = View.GONE
                if (state.data !=null) {
                        val adapter = MainAdapter(state.data)
                        val recyclerView = findViewById<RecyclerView>(R.id.characterRv)
                        recyclerView?.layoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }
            }
            is ScreenState.Error -> {
                progressBar.visibility = View.GONE
                val view = progressBar.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}