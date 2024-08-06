package com.fatima.hospitaldata.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatima.hospitaldata.MainActivity
import com.fatima.hospitaldata.database.AppDatabase
import com.fatima.hospitaldata.database.Patient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PatientViewModel(context: Context): ViewModel() {

    private val database: AppDatabase =AppDatabase.getDatabase(context)

    fun savePatient(patient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            // Perform database operations
            database.patientDao().insert(patient);
            // Update LiveData or StateFlow with the fetched data if needed
        }
    }
    fun updatePatient(patient: Patient){
        viewModelScope.launch(Dispatchers.IO) {
            // Perform database operations
            database.patientDao().update(patient);
            // Update LiveData or StateFlow with the fetched data if needed
        }
    }
    fun getPatients(): Flow<List<Patient>> = database.patientDao().allPatients();
}