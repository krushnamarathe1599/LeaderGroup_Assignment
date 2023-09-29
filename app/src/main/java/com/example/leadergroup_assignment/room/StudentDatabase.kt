package com.example.leadergroup_assignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.leadergroup_assignment.model.Student

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): RoomDao

    companion object {
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, StudentDatabase::class.java, "studentDB").build()

            }
            return INSTANCE!!
        }
    }
}