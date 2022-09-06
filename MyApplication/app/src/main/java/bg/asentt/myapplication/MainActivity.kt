package bg.asentt.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

//https://www.youtube.com/watch?v=QBuTsKM2dh4
//https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
//https://developer.android.com/training/data-storage/shared-preferences
class MainActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    private lateinit var username: String
    private lateinit var tvData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        checkLogin()
        setupUI()
        val logoutButton: Button = findViewById(R.id.button_logout)
        logoutButton.setOnClickListener {
            clickLogout()
        }

    }

    private fun init(){
        prefManager = PrefManager(this)
        username = prefManager.getUsername().toString()
        tvData = findViewById(R.id.tv_data)
        tvData.text = username
    }

    private fun checkLogin() {
        if (prefManager.isLogin() == false) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupUI() {
        tvData.text = "Hello, $username!"
    }

    fun clickLogout() {
        prefManager.removeData()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}