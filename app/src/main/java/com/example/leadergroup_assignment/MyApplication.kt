package com.example.leadergroup_assignment

import android.app.Application
import com.example.leadergroup_assignment.api.ApiInterface
import com.example.leadergroup_assignment.api.ApiUtilities
import com.example.leadergroup_assignment.repository.StudentRepository
import com.example.leadergroup_assignment.room.StudentDatabase

class MyApplication : Application() {

    lateinit var studentRepository: StudentRepository
    override fun onCreate() {
        super.onCreate()

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val database = StudentDatabase.getDatabase(applicationContext)

        studentRepository = StudentRepository(apiInterface, database, applicationContext)
    }
}