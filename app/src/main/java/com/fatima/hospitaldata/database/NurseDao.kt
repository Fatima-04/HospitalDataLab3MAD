package com.fatima.hospitaldata.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NurseDao {

    @Insert
    fun insert(nurse: Nurse)

    @Update
    fun update(nurse: Nurse)

    @Delete
    fun delete(nurse: Nurse)

    @Query("SELECT * FROM nurse WHERE nurseId = :nurseId AND password = :password")
    fun login(nurseId: Int, password: Int): Flow<List<Nurse>>

    @Query("SELECT * FROM nurse")
    fun allNurses(): Flow<List<Nurse>>
}
