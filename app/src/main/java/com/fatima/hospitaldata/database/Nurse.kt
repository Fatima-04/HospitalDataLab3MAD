package com.fatima.hospitaldata.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nurse (

        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name="nurseId") val nurseId: Int,
        @ColumnInfo(name="password") val password: Int,
        @ColumnInfo(name="firstName") val firstName: String,
        @ColumnInfo(name="lastName") val lastName: String,
        @ColumnInfo(name="department") val department: String
)
        // {
//        constructor(id: Int,nurseId: Int, password: Int, firstName: String,lastName: String, department: String): this(
//                id= id,
//                nurseId= nurseId,
//                password = password,
//                firstName = firstName,
//                lastName = lastName,
//                department = department
//        )
//}

