package bg.asenterziyski.testgooglesigninfragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

class GoogleSignInRepository(anActivity: Context) {

    private val gso = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestId()
        .build()

    private val gsc = GoogleSignIn.getClient(anActivity, gso)

    fun hasLastSignInAccount(context: Context): Boolean {
        return getAccount(context) != null
    }

    fun getSignInIntent(): Intent {
        return gsc.signInIntent
    }

    fun getAccount(context: Context): GoogleAccount? {
        val account = GoogleSignIn.getLastSignedInAccount(context) ?: return null
        return Mapper().mapGoogleSignInAccountToGoogleAccount(account)
    }

    fun signOut(): Task<Void>? {
        return gsc.signOut()
    }

}