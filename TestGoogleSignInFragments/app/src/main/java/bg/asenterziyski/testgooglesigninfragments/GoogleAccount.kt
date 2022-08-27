package bg.asenterziyski.testgooglesigninfragments

data class GoogleAccount(
    val name: String?,
    val email: String?,
    val id: String?,
    val idToken: String?,
    val expired: Boolean,
    val accountType: String?,
    val serverAuthCode: String?,
    val photoUrl: String
) {}