package com.fatima.hospitaldata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.fatima.hospitaldata.database.Nurse
import com.fatima.hospitaldata.viewmodels.NurseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NurseLogin : AppCompatActivity() {
    lateinit var nurseViewModel: NurseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        nurseViewModel = NurseViewModel(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nurse_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nurseLogin = findViewById<Button>(R.id.btnNurseLogin)
        nurseLogin.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                val nurseCollect = nurseViewModel.loginNurse(findViewById<EditText>(R.id.nurse_id).text.toString().toInt(), findViewById<EditText>(R.id.password).text.toString().toInt())
                nurseCollect.collect(){ itValue: List<Nurse> ->
                    if(itValue.isNotEmpty()){
                       withContext(Dispatchers.Main){
                          // Toast.makeText(this@NurseLogin,"Successful",Toast.LENGTH_LONG).show()
                        val intent = Intent(this@NurseLogin,PatientNavigation::class.java)
                        startActivity(intent)
                       }
                   }
                    else{
                       withContext(Dispatchers.Main){
                        Toast.makeText(this@NurseLogin,"Invalid Entry",Toast.LENGTH_LONG).show()

                       }
                   }
                }
            }

        }

    }

}