package bg.asentt.myapplication

import android.content.Intent
import android.icu.text.Edits
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var username: String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        checkLogin()
        val loginButton = findViewById<Button>(R.id.button_login)
        loginButton.setOnClickListener {
            clickLogin()
        }
    }

    private fun init() {
        prefManager = PrefManager(this)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
    }

    private fun checkLogin() {
        if (prefManager.isLogin() == true) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun clickLogin() {
        username = etUsername.text.toString().trim()
        password = etPassword.text.toString().trim()

        if (username.isEmpty() || username == "") {
            etUsername.error = "Empty username!"
            etUsername.requestFocus()
        } else if (password.isEmpty() || password == "") {
            etPassword.error = "Empty password!"
            etPassword.requestFocus()
        }
//        else if (username != validUsername) {
//            etUsername.error = "Invalid username!"
//            etUsername.requestFocus()
//        } else if (password != validPassword) {
//            etPassword.error = "Invalid password!"
//            etPassword.requestFocus()
//        }
        else {
            prefManager.setLogin(true)
            prefManager.setUsername(username)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}