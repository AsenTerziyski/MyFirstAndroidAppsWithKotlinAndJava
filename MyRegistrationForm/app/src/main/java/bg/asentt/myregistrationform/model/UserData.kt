package bg.asentt.myregistrationform.model

import java.io.Serializable

data class UserData(
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val password: String
) : Serializable {

    fun getFullName(): String {
        return "$title $firstName $lastName"
    }

}
