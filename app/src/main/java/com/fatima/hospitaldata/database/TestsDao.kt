package com.fatima.hospitaldata.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TestsDao {

    @Insert
    fun insert(tests: Tests)

    @Update
    fun update(tests: Tests)

    @Delete
    fun delete(tests: Tests)

    @Query("SELECT * FROM tests WHERE test_patient_Id = :patientId")
    fun gettestsBypatientId(patientId: Int): Flow<List<Tests>>?

    @Query("SELECT * FROM tests")
    fun allTests(): Flow<List<Tests>>
}