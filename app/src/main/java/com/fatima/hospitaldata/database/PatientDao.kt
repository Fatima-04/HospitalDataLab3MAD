package com.fatima.hospitaldata.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Insert
    fun insert(patient: Patient)

    @Update
    fun update(patient: Patient)

    @Delete
    fun delete(patient: Patient)

    @Query("SELECT * FROM patient WHERE id = :patientId")
    fun getPatientById(patientId: Int): Flow<List<Patient>>?

    @Query("SELECT * FROM patient")
    fun allPatients(): Flow<List<Patient>>
}
