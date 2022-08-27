package bg.asenterziyski.testgooglesigninfragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import bg.asenterziyski.testgooglesigninfragments.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient


//https://developers.google.com/identity/sign-in/android/start-integrating
////ClientID: ???ClientId
////ClientSecret:  ?????
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