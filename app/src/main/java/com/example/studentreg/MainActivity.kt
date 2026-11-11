package com.example.studentreg



import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Link each widget to its id in the layout
        val etStudentName = findViewById<EditText>(R.id.etStudentName)
        val etRegNumber = findViewById<EditText>(R.id.etRegNumber)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spnProgramme = findViewById<Spinner>(R.id.spnProgramme)
        val rgStudyMode = findViewById<RadioGroup>(R.id.rgStudyMode)
        val swNotifications = findViewById<SwitchCompat>(R.id.swNotifications)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // Runs only when Submit is tapped
        btnSubmit.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val regNumber = etRegNumber.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (name.isEmpty()) {
                etStudentName.error = "Please enter the student name"
                etStudentName.requestFocus()
                return@setOnClickListener
            }
            if (regNumber.isEmpty()) {
                etRegNumber.error = "Please enter the registration number"
                etRegNumber.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                etEmail.error = "Please enter the email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter a valid email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (rgGender.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please select a gender option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (spnProgramme.selectedItemPosition == 0) {
                Toast.makeText(this, "Please select a study programme", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (rgStudyMode.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please select a study mode", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val programme = spnProgramme.selectedItem.toString()
            val notifications = if (swNotifications.isChecked) "On" else "Off"

            Toast.makeText(
                this,
                "Registration successful!\n$name ($regNumber)\n$programme\nNotifications: $notifications",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}