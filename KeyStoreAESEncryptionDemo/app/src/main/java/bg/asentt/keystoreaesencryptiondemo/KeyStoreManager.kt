package bg.asentt.keystoreaesencryptiondemo

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object KeyStoreManager {

    private const val PROVIDER = "AndroidKeyStore"
    private const val KEY_STORE_ALIAS = "MyTestKeyAlias"
    private const val PURPOSES = KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    private const val TRANSFORMATION = "AES/CBC/NoPadding"

    private val keyGenarator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER)

    private val keyGenParamSpec = KeyGenParameterSpec.Builder(KEY_STORE_ALIAS, PURPOSES)
        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .build()

    private val keyStore = KeyStore.getInstance(PROVIDER)

    fun generateKey() {
        keyGenarator.init(keyGenParamSpec)
        keyGenarator.generateKey()
    }

    private fun getKey(): SecretKey {
        keyStore.load(null)
        val secretKeyEntry = keyStore
            .getEntry(KEY_STORE_ALIAS, null) as KeyStore.SecretKeyEntry
        return secretKeyEntry.secretKey
    }

    fun encryptData(data: String): Pair<ByteArray, ByteArray> {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        var temp = data
        while (temp.toByteArray().size % 16 != 0) {
            temp += "\u0020"
        }
        cipher.init(Cipher.ENCRYPT_MODE, getKey())
        val ivBytes = cipher.iv
        val encryptedBytes = cipher.doFinal(temp.toByteArray(Charsets.UTF_8))
        return Pair(ivBytes, encryptedBytes)
    }

    fun decryptData(ivBytes: ByteArray, data: ByteArray): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = IvParameterSpec(ivBytes)
        cipher.init(Cipher.DECRYPT_MODE, getKey(), spec)
        return cipher.doFinal(data).toString(Charsets.UTF_8).trim()
    }

}