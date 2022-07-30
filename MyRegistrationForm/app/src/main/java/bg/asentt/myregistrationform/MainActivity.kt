package bg.asentt.myregistrationform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import bg.asentt.myregistrationform.model.UserData
import bg.asentt.myregistrationform.model.enums.TitlesEnum
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var createAccountButton: MaterialButton

    private lateinit var spinnerTitle: Spinner
    private lateinit var firstName: TextInputEditText
    private lateinit var lastName: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var phoneNumber: TextInputEditText
    private lateinit var password: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        setupSpinner()
        setupButtonClickListener()
    }

    private fun bindViews() {
        firstName = edit_text_first_name
        lastName = edit_text_last_name
        email = edit_text_email
        phoneNumber = edit_text_phone
        password = edit_text_password
        createAccountButton = button_create_account
    }

    private fun setupSpinner() {
        spinnerTitle = spinner_title
        val titles = TitlesEnum.values().map { it.title }
        val titleAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            titles
        )
        spinnerTitle.adapter = titleAdapter
    }

    private fun setupButtonClickListener() {
        createAccountButton.setOnClickListener {
            createUserAccount()
            passUserToSummaryActivity(createUserAccount())
        }
    }

    private fun createUserAccount(): UserData {
        return UserData(
            spinnerTitle.selectedItem as String,
            firstName.text.toString(),
            lastName.text.toString(),
            email.text.toString(),
            phoneNumber.text.toString(),
            password.text.toString()
        )
    }

    private fun passUserToSummaryActivity(user: UserData) {
        val intent = Intent(this, SummaryActivity::class.java)
        intent.putExtra("User", user)
        startActivity(intent)
    }

}