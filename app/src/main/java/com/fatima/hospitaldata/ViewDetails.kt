package com.fatima.hospitaldata

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.fatima.hospitaldata.database.Patient
import com.fatima.hospitaldata.viewmodels.PatientViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.gson.Gson

class ViewDetails : AppCompatActivity() {
    var patientList : List<Patient> = listOf()
    lateinit var patientViewModel : PatientViewModel
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var listOfPatients: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        patientViewModel = PatientViewModel(this)
        lifecycleScope.launch(Dispatchers.IO) {

            patientViewModel.getPatients().collect(){
                patientList = it
                withContext(Dispatchers.Main){
                    arrayAdapter = ArrayAdapter(this@ViewDetails, android.R.layout.simple_list_item_1, patientList.map { patient -> patient.firstName + " " + patient.lastName })
                    listOfPatients.adapter = arrayAdapter

                    listOfPatients.setOnItemClickListener { parent, view, position, id ->
                        val selectedPatient = patientList[position]
                        Toast.makeText(applicationContext,"Selected Patient" ,Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@ViewDetails, PatientInfo::class.java)
                        intent.putExtra("selectedPatientP", Gson().toJson(selectedPatient))
                        startActivity(intent)
                    }
                }
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patientList.map { patient -> patient.firstName + " " + patient.lastName })
        listOfPatients = findViewById<ListView>(R.id.listViewOfPatients)
        listOfPatients.adapter = arrayAdapter
    }
}