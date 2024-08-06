package com.fatima.hospitaldata

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.fatima.hospitaldata.database.AppDatabase
import com.fatima.hospitaldata.database.Patient
import com.fatima.hospitaldata.viewmodels.PatientViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
        val login_button = findViewById<Button>(R.id.button4)
        login_button.setOnClickListener {
            val intent = Intent(this,NurseLogin::class.java)
            startActivity(intent)
        }
        val loginNewNurse = findViewById<Button>(R.id.btn_new_nurse)
        loginNewNurse.setOnClickListener {
            val intent = Intent(this,RegisterNurse::class.java)
            startActivity(intent)
        }
    }
}