package com.example.leadergroup_assignment.api

import com.example.leadergroup_assignment.model.StudentDetails
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("6a9d0c64-0446-4820-a44a-e2de48503539")
    suspend fun getStudentDetails(): Response<StudentDetails>
}