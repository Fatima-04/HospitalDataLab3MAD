package com.fatima.hospitaldata.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name="department") val department: String,
    @ColumnInfo(name="nurseId") val nurseId: Int,
    @ColumnInfo(name="roomNo") val room: Int
) : Serializable

