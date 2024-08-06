package com.fatima.hospitaldata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PatientNavigation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_navigation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navRegPatient = findViewById<Button>(R.id.btn_nav_register)
        navRegPatient.setOnClickListener {

            val intent = Intent(this, PatientInfo::class.java)
            startActivity(intent)

        }

        val navDetails = findViewById<Button>(R.id.btn_nav_details)
        navDetails.setOnClickListener {

            val intent = Intent(this,   ViewDetails::class.java)
            startActivity(intent)

        }

    }
}