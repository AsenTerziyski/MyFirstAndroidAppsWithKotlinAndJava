package bg.asentt.bmicalculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TOAST_MESSAGE = "Welcome to BMI Calculator!";
    // Class variables als called fields
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;
    private TextView resultText;
    private double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast
                .makeText(this, TOAST_MESSAGE, Toast.LENGTH_SHORT)
                .show();

        findViews();
        setCalculateButtonClickListener();
    }

    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setCalculateButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAge();
                calculateBmi();
            }
        });
    }

    private void checkAge() {
        if (ageEditText.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter age.", Toast.LENGTH_SHORT).show();
            return;
        }
        int age = Integer.parseInt(ageEditText.getText().toString());

        if (age >= 18) {
            toastBmiInterpretation(bmi);
        } else {
            toastGuidance();
        }
    }

    private void calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        if (feetText.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter feet.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (inchesText.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter inches.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (weightText.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter weight.", Toast.LENGTH_SHORT).show();
            return;
        }

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = feet * 12 + inches;
        double heightInMeters = totalInches * 0.0254;
        bmi = weight / Math.pow(heightInMeters, 2.0);

        String bmiTextResult = String.format("Your BMI is: %.2f", bmi);
        resultText.setText(bmiTextResult);
    }


    private void toastBmiInterpretation(double bmi) {

        if (bmi < 18.50000) {
            Toast.makeText(MainActivity.this, "Underweight", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bmi < 24.90000) {
            Toast.makeText(MainActivity.this, "Healthy weight", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bmi < 29.90000) {
            Toast.makeText(MainActivity.this, "Over weight", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(MainActivity.this, "Obesity", Toast.LENGTH_SHORT).show();
    }

    private void toastGuidance() {

        if (maleButton.isChecked()) {
            Toast
                    .makeText(MainActivity.this, "You are under 18. Please consult your doctor for healthy range for boys!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (femaleButton.isChecked()) {
            Toast
                    .makeText(MainActivity.this, "You are under 18. Please consult your doctor for healthy range for girls!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Toast.makeText(MainActivity.this, "Your age is under 18. Please consult your doctor for healthy range.", Toast.LENGTH_SHORT).show();
    }
}