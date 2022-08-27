package bg.asenterziyski.testgooglesigninfragments


import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class Mapper {
    fun mapGoogleSignInAccountToGoogleAccount(account: GoogleSignInAccount): GoogleAccount {
        return GoogleAccount(
            account.displayName,
            account.email,
            account.id,
            account.idToken,
            account.isExpired,
            account.account?.type,
            account.serverAuthCode,
            account.photoUrl.toString()
        )
    }
}