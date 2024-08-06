package com.fatima.hospitaldata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Patient::class,Nurse::class,Tests::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun nurseDao(): NurseDao
    abstract fun testsDao(): TestsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database6")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
