package bg.asenterziyski.testgooglesigninfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bg.asenterziyski.testgooglesigninfragments.databinding.ActivityMainBinding

//https://developers.google.com/identity/sign-in/android/start-integrating
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
    }

    private fun bind() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}