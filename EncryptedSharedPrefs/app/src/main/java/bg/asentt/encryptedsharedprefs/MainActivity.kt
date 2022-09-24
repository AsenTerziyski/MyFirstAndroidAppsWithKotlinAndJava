package bg.asentt.encryptedsharedprefs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

//https://www.youtube.com/watch?v=51sU9NZTXQA&list=PLDP3jMsmhGQVSkNFQKqNKnGNYUTMuV6W7
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val encryptedSharedPrefsInstance = EncryptedSharedPrefsManager
            .getEncryptedSharedPrefsInstance(this)

        EncryptedSharedPrefsManager.putEmail("test@test.bg")
        EncryptedSharedPrefsManager.putAccessToken("testAccessToken")
        EncryptedSharedPrefsManager.putRefreshToken("testRefreshToken")
        EncryptedSharedPrefsManager.putRefreshTokenExpDate("2022-10-01")

        Log.d("SharedPrefs", "Instance -> $encryptedSharedPrefsInstance")
        Log.d("SharedPrefs", "AccessToken -> " + EncryptedSharedPrefsManager.getAccessToken())
        Log.d("SharedPrefs", "RefreshToken -> " + EncryptedSharedPrefsManager.getRefreshToken())
        Log.d("SharedPrefs", "ExpDate -> " + EncryptedSharedPrefsManager.getRefreshTokenExpDate())
        Log.d("SharedPrefs", "Email -> " + EncryptedSharedPrefsManager.getEmail())
    }
}