package com.fatima.hospitaldata

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.fatima.hospitaldata.database.Patient
import com.fatima.hospitaldata.database.Tests
import com.fatima.hospitaldata.viewmodels.PatientViewModel
import com.fatima.hospitaldata.viewmodels.TestsViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewTestDetails : AppCompatActivity() {
    var testList : List<Tests> = listOf()
    lateinit var testsViewModel: TestsViewModel
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var listOfTests : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        testsViewModel = TestsViewModel(this)
        val receivePatientId = intent.getStringExtra("patientId").toString().toInt()


        lifecycleScope.launch(Dispatchers.IO) {

            testsViewModel.allTest(patientId = receivePatientId)?.collect(){
                testList = it
                withContext(Dispatchers.Main){
                    arrayAdapter = ArrayAdapter(this@ViewTestDetails,android.R.layout.simple_list_item_1, testList.map { tests -> tests.testName })
                    listOfTests.adapter = arrayAdapter
                }
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_test_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listOfTests = findViewById<ListView>(R.id.List_view_tests)
    }
}