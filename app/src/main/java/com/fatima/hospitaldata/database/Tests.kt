package com.fatima.hospitaldata.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tests(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "test_patient_Id") val test_patient_Id: Int,
    @ColumnInfo(name = "test_nurse_id") val nurseId: Int,
    @ColumnInfo(name="test_name") val testName: String,
    //@ColumnInfo(name="BPH") val BPH: Int,
    //@ColumnInfo(name="temperature") val temperature: Int
)