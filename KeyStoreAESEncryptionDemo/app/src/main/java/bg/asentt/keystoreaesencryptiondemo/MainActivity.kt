package bg.asentt.keystoreaesencryptiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

//https://www.youtube.com/watch?v=Adqcm-BFioo&list=PLDP3jMsmhGQVSkNFQKqNKnGNYUTMuV6W7&index=3
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        KeyStoreManager.generateKey()
        val pair = KeyStoreManager.encryptData("TestString to encrypt: ABC abc 123456789 !#@$%^&*!")
        val encryptedData = pair.second.toString(Charsets.UTF_8)
        val decryptedData = KeyStoreManager.decryptData(pair.first, pair.second)

        Log.d("DecryptedEncryptedData", "Encrypted: $encryptedData")
        Log.d("DecryptedEncryptedData", "Decrypted: $decryptedData")

        // generate the key
//        val keyGenarator = KeyGenerator
//            .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
//
//        val keyGenParamSpec = KeyGenParameterSpec.Builder(
//            "MyTestKeyAlias",
//            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
//        )
//            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
//            .build()

//        keyGenarator.init(keyGenParamSpec)
//        keyGenarator.generateKey()
//
//        val pair = encryptData("TEST STRING")
//        val encrypted = pair.second.toString(Charsets.UTF_8)
//        val decryptData = decryptData(pair.first, pair.second)
//
//        Log.d("keyGenerator", "Encrypted: $encrypted")
//        Log.d("DecryptedEncryptedData", "Decrypted: $decryptData")

    }

//    private fun getKey(): SecretKey {
//        val keyStore = KeyStore.getInstance("AndroidKeyStore")
//        keyStore.load(null)
//        val secretKeyEntry = keyStore.getEntry("MyTestKeyAlias", null) as KeyStore.SecretKeyEntry
//        return secretKeyEntry.secretKey
//    }
//
//    private fun encryptData(data: String): Pair<ByteArray, ByteArray> {
//        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
//
//        var temp = data
//        while (temp.toByteArray().size % 16 != 0) {
//            temp += "\u0020"
//        }
//
//        cipher.init(Cipher.ENCRYPT_MODE, getKey())
//        val ivBytes = cipher.iv
//        val encryptedBytes = cipher.doFinal(temp.toByteArray(Charsets.UTF_8))
//        return Pair(ivBytes, encryptedBytes)
//    }
//
//    private fun decryptData(ivBytes: ByteArray, data: ByteArray): String {
//        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
//        val spec = IvParameterSpec(ivBytes)
//        cipher.init(Cipher.DECRYPT_MODE, getKey(), spec)
//        return cipher.doFinal(data).toString(Charsets.UTF_8).trim()
//
//    }
}