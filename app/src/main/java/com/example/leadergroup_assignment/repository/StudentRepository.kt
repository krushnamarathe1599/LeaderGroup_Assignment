package com.example.leadergroup_assignment.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leadergroup_assignment.api.ApiInterface
import com.example.leadergroup_assignment.model.StudentDetails
import com.example.leadergroup_assignment.room.StudentDatabase
import com.example.leadergroup_assignment.util.MyUtils

class StudentRepository(

    private val apiInterface: ApiInterface,
    private val studentDatabase: StudentDatabase,
    private val applicationContext: Context
) {

    private val TAG = "StudentRepository"
    private val studentLiveData = MutableLiveData<StudentDetails>()

    val studentList : LiveData<StudentDetails>
        get() = studentLiveData

    suspend fun getStudent(){
        if (MyUtils.isInternetAvailable(applicationContext)){

            val result = apiInterface.getStudentDetails()
            if (result.body()!=null) {
                Log.d(TAG, "Response Body = " + result.body())
                studentDatabase.studentDao().insertStudent(result.body()!!.students)
                Log.d(TAG, "Data Inserted Into Database")
                studentLiveData.postValue(result.body())
            }else{
                Log.e(TAG, "Failed to load student data")
            }
        }
        else{
            Log.e(TAG, "Failed to connect to internet loading data from local database")
            val student = studentDatabase.studentDao().getStudent()

            val studentList = StudentDetails("success",student)

            studentLiveData.postValue(studentList)
        }
    }

}