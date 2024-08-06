package com.fatima.hospitaldata.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatima.hospitaldata.database.AppDatabase
import com.fatima.hospitaldata.database.Nurse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NurseViewModel (context: Context) : ViewModel(){

        private val database: AppDatabase = AppDatabase.getDatabase(context)

        fun saveNurse(nurse: Nurse) {
            viewModelScope.launch(Dispatchers.IO) {
                database.nurseDao().insert(nurse)
            }
        }

        fun loginNurse(nurseId: Int, password: Int): Flow<List<Nurse>> {
            return database.nurseDao().login(nurseId, password)
        }

    fun allNurses():Flow<List<Nurse>>{
        return database.nurseDao().allNurses()
    }
}
