package bg.asentt.myregistrationform

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bg.asentt.myregistrationform.model.UserData
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val user = retrieveUser()
        displayUser(user)
        setupClickListeners(user)
    }

    private fun retrieveUser(): UserData {
        return intent.getSerializableExtra("User") as UserData
    }

    private fun displayUser(user: UserData) {
        text_view_user_full_name.text = user.getFullName()
        text_view_email.text = user.email
        text_view_phone.text = user.phoneNumber
    }

    private fun setupClickListeners(user: UserData) {

        text_view_email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${user.email}")
            startActivity(intent)
        }

        text_view_phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${user.phoneNumber}")
            startActivity(intent)
        }

    }

}