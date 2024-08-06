package com.fatima.hospitaldata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.fatima.hospitaldata.database.AppDatabase
import com.fatima.hospitaldata.database.Patient
import com.fatima.hospitaldata.viewmodels.PatientViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PatientInfo : AppCompatActivity() {
    private lateinit var patientViewModel : PatientViewModel
    var selectedPatientJson: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        patientViewModel = PatientViewModel(this)
        val registerPatient = findViewById<Button>(R.id.btn_register_patient)
        registerPatient.setOnClickListener {
            regPatient()
            val intent = Intent(this, ViewDetails::class.java)
            startActivity(intent)
        }

        selectedPatientJson = intent.getStringExtra("selectedPatientP")
        val selectedItem = Gson().fromJson(selectedPatientJson,Patient::class.java)
        if(selectedPatientJson != null){
            displayPatient(selectedItem)
        }

        val btnAddTests = findViewById<Button>(R.id.btn_add_tests_info)
        btnAddTests.setOnClickListener {

            val intent = Intent(this, EnterTests::class.java)
            intent.putExtra("patientId",findViewById<TextView>(R.id.patient_id_info).text.toString())
            intent.putExtra("nurseId",findViewById<TextView>(R.id.nurse_id_info).text.toString())
            startActivity(intent)
        }

        val btnViewAllTests = findViewById<Button>(R.id.btn_view_tests)
        btnViewAllTests.setOnClickListener {

            val intent = Intent(this, ViewTestDetails::class.java)
            intent.putExtra(
                "patientId",
                findViewById<TextView>(R.id.patient_id_info).text.toString()
            )
            startActivity(intent)
        }
    }

    fun regPatient() {

        val patientId_info = findViewById<TextView>(R.id.patient_id_info).text.toString().toInt()
        val firstName_info = findViewById<TextView>(R.id.first_name_info).text.toString()

        val lastName_info = findViewById<TextView>(R.id.last_name_info).text.toString()

        val depart_info = findViewById<TextView>(R.id.dept_info).text.toString()

        val nurseId_info = findViewById<TextView>(R.id.nurse_id_info).text.toString().toInt()
        val roomNo_info = findViewById<TextView>(R.id.room_info).text.toString().toInt()

        val patient = Patient(patientId_info, firstName_info,lastName_info,depart_info, nurseId_info, roomNo_info)

        if(selectedPatientJson != null){
            patientViewModel.updatePatient(patient)
        }else{
            patientViewModel.savePatient(patient)
        }
    }

    fun displayPatient(patient: Patient) {
        findViewById<EditText>(R.id.patient_id_info).setText(patient.id.toString())
        findViewById<EditText>(R.id.first_name_info).setText(patient.firstName)
        findViewById<EditText>(R.id.last_name_info).setText(patient.lastName)
        findViewById<EditText>(R.id.dept_info).setText(patient.department)
        findViewById<EditText>(R.id.nurse_id_info).setText(patient.nurseId.toString())
        findViewById<EditText>(R.id.room_info).setText(patient.room.toString())
    }
}