package com.fatima.hospitaldata.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.fatima.hospitaldata.database.AppDatabase
import com.fatima.hospitaldata.database.Nurse
import com.fatima.hospitaldata.database.Patient
import com.fatima.hospitaldata.database.Tests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TestsViewModel(context: Context) :ViewModel() {

    private val database: AppDatabase = AppDatabase.getDatabase(context)

    fun saveTest(tests: Tests){
        viewModelScope.launch(Dispatchers.IO) {
            database.testsDao().insert(tests)
        }
    }
    fun allTest(patientId:Int):Flow<List<Tests>>?{
        return database.testsDao().gettestsBypatientId(patientId)

    }
}
