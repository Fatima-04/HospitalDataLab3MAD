package com.fatima.hospitaldata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fatima.hospitaldata.database.Patient
import com.fatima.hospitaldata.database.Tests
import com.fatima.hospitaldata.viewmodels.TestsViewModel

class EnterTests : AppCompatActivity() {

    val testsViewModel: TestsViewModel= TestsViewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_tests)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val testsSubmit = findViewById<Button>(R.id.btn_submit_test)
        val testPatientId = findViewById<EditText>(R.id.test_patients_id)
        val testNurseId = findViewById<TextView>(R.id.test_nurse_id)
        val testsName = findViewById<TextView>(R.id.test_name)

        val receivePatientId = intent.getStringExtra("patientId")
        val receivedNurseId = intent.getStringExtra("nurseId")

        testPatientId.setText(receivePatientId)
        testNurseId.setText(receivedNurseId)

        testsSubmit.setOnClickListener {

            val tests = Tests(test_patient_Id = testPatientId.text.toString().toInt(), nurseId = testNurseId.text.toString().toInt(), testName = testsName.text.toString())
            testsViewModel.saveTest(tests)

            val intent = Intent(this,ViewTestDetails::class.java)
            intent.putExtra("patientId",testPatientId.text.toString())
            startActivity(intent)
        }

    }

}