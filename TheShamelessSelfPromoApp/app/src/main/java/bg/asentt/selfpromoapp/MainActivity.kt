package bg.asentt.selfpromoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var previewButton: Button
    private lateinit var contactNameEditText: TextInputEditText
    private lateinit var contactNumberEditText: TextInputEditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        previewButton.setOnClickListener {
            onPreviewClicked()
        }

    }

    private fun findViews() {
        previewButton = findViewById<Button>(R.id.button_preview)
        contactNameEditText = findViewById(R.id.edit_text_contact_name)
        contactNumberEditText = findViewById(R.id.edit_text_contact_number)
    }

    private fun onPreviewClicked() {
        Toast.makeText(
            this,
            "${contactNameEditText.text} -> ${contactNumberEditText.text}",
            Toast.LENGTH_LONG
        ).show()
    }
}