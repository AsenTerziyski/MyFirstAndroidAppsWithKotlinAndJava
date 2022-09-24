package bg.asentt.encryptedsharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object EncryptedSharedPrefsManager {

    private var encryptedSharedPrefs: SharedPreferences? = null

    private val spec = KeyGenParameterSpec.Builder(
        "_androidx_security_master_key_",
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(256)
        .build()

    private const val FILE_NAME = "EncryptedSharedPrefsFile"

    fun getEncryptedSharedPrefsInstance(context: Context): SharedPreferences {
        if (encryptedSharedPrefs == null) {

            val masterKey: MasterKey = MasterKey.Builder(context)
                .setKeyGenParameterSpec(spec)
                .build()

            encryptedSharedPrefs = EncryptedSharedPreferences.create(
                context,
                FILE_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

        }
        return encryptedSharedPrefs!!
    }

    fun putAccessToken(accessToken: String) {
        if (encryptedSharedPrefs != null) {
            encryptedSharedPrefs!!
                .edit()
                .putString("accessToken", accessToken)
                .apply()
        }
    }

    fun getAccessToken(): String {
        return encryptedSharedPrefs!!
            .getString("accessToken", null)
            .toString()
    }

    fun putRefreshToken(refreshToken: String) {
        if (encryptedSharedPrefs != null) {
            encryptedSharedPrefs!!
                .edit()
                .putString("refreshToken", refreshToken)
                .apply()
        }
    }

    fun getRefreshToken(): String {
        return encryptedSharedPrefs!!.getString("refreshToken", null).toString()
    }

    fun putRefreshTokenExpDate(refreshTokenExpDate: String) {
        if (encryptedSharedPrefs != null) {
            encryptedSharedPrefs!!
                .edit()
                .putString("refreshTokenExpDate", refreshTokenExpDate)
                .apply()
        }
    }

    fun getRefreshTokenExpDate(): String {
        return encryptedSharedPrefs!!.getString("refreshTokenExpDate", null).toString()
    }

    fun putEmail(email: String) {
        if (encryptedSharedPrefs != null) {
            encryptedSharedPrefs!!
                .edit()
                .putString("email", email)
                .apply()
        }
    }

    fun getEmail(): String {
        return encryptedSharedPrefs!!.getString("email", null).toString()
    }
}