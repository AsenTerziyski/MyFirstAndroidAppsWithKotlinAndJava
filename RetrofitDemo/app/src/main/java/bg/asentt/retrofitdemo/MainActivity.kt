package bg.asentt.retrofitdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_create_new_user.*
import kotlinx.android.synthetic.main.activity_main.*

/*
https://www.youtube.com/watch?v=4JGvDUlfk7Y
also watch this: https://www.youtube.com/watch?v=ARpn-1FPNE4&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118
 */

/*
https://gorest.co.in/
https://www.youtube.com/watch?v=TJpk7ezvtGo - do 27min
https://github.com/ravizworldz/retrofit2_post_patch_delete

m v v n explained (increase int demo) -> https://www.youtube.com/watch?v=orH4K6qBzvE
 */
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
        searchUser()

        createUserButton.setOnClickListener {
            startActivity(Intent(this, CreateNewUserActivity::class.java))
        }
    }

    private fun searchUser() {
        search_button.setOnClickListener {
            if (!TextUtils.isEmpty(searchEditText.text.toString())) {
                viewModel.searchUser(searchEditText.text.toString())
            } else {
                // if search box is empty -> return whole list:
                viewModel.getUserList()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
        }
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel
            .getUserListObserver()
            .observe(this, Observer<UsersList> {
                if (it == null) {
                    Toast.makeText(this@MainActivity, "No result", Toast.LENGTH_LONG).show()
                } else {
                    recyclerViewAdapter.userList = it.data.toMutableList()
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            })
        viewModel.getUserList()
    }

    override fun onItemEditCLick(user: User) {
        val intent = Intent(this@MainActivity, CreateNewUserActivity::class.java)
        intent.putExtra("user_id", user.id)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000) {
            viewModel.getUserList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}