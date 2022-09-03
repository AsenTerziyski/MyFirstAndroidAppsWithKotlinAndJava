package bg.asentt.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_new_user.*

class CreateNewUserActivity : AppCompatActivity() {
    lateinit var viewModel: CreateNewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)
        initViewModel()
        val user_id = intent.getStringExtra("user_id")

        createNewUserObservable()


        if (user_id != null) {
            loadUserData(user_id)
        }


        createButton.setOnClickListener {
            createUser(user_id)
        }
    }

    private fun loadUserData(userId: String?) {
        viewModel.getLoadUserObservable().observe(this, Observer<UserResponse?> {
            if (it != null) {
                editTextName.setText(it.data.name)
                editTextEmail.setText(it.data.email)
                createButton.setText("Update!")
                deleteButton.isVisible = false
            }
        })
        viewModel.getUserData(userId)
    }

    private fun createUser(user_id: String?) {

        val newUser = User(
            "",
            editTextName.text.toString(),
            editTextEmail.text.toString(),
            "Active", "Male"
        )

        if (user_id == null) {
            viewModel.createUser(newUser)
        } else {
            viewModel.updateUser(user_id,newUser)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CreateNewUserViewModel::class.java]
    }

    private fun createNewUserObservable() {
        viewModel.getCreateNewUserObservable().observe(this, Observer<UserResponse?> {
            if (it == null) {
                Toast.makeText(
                    this@CreateNewUserActivity,
                    "Failed to create/update a user!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val name = it.data.name
                Toast.makeText(this@CreateNewUserActivity, "Created/updated $name!!!", Toast.LENGTH_LONG)
                    .show()
                finish()
            }
        })
    }
}