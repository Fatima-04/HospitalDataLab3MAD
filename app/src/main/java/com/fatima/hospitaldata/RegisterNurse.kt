package com.fatima.hospitaldata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fatima.hospitaldata.database.Nurse
import com.fatima.hospitaldata.viewmodels.NurseViewModel

class RegisterNurse : AppCompatActivity() {

    private lateinit var nurseViewModel: NurseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_patient)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nurseViewModel = NurseViewModel(this)

        val registerNurseButton = findViewById<Button>(R.id.btn_register_nurse)
        registerNurseButton.setOnClickListener {
            nurseRegister()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun nurseRegister() {
        val nurseId = findViewById<EditText>(R.id.nurse_id_reg).text.toString().toInt()
        val firstName = findViewById<EditText>(R.id.nurse_first_name).text.toString()
        val lastName = findViewById<EditText>(R.id.nurse_last_name).text.toString()
        val department = findViewById<EditText>(R.id.nurse_register_dept).text.toString()
        val password = findViewById<EditText>(R.id.nurse_password).text.toString().toInt()

        val nurse = Nurse(
            nurseId = nurseId,
            password = password,
            firstName = firstName,
            lastName = lastName,
            department = department
        )

        nurseViewModel.saveNurse(nurse)
    }
}
