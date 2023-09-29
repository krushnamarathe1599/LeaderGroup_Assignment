package com.example.leadergroup_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.leadergroup_assignment.Constants.Companion.STUDENT_DATA
import com.example.leadergroup_assignment.model.Student

class DetailsActivity : AppCompatActivity() {

    val TAG = DetailsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val resultFirstName = findViewById<TextView>(R.id.result_firstName)
        val resultLastName = findViewById<TextView>(R.id.result_lastName)
        val resultAge = findViewById<TextView>(R.id.result_age)
        val resultGender = findViewById<TextView>(R.id.result_gender)
        val resultMajor = findViewById<TextView>(R.id.result_major)
        val resultGpa = findViewById<TextView>(R.id.result_gpa)
        val student :Student = intent.getParcelableExtra(STUDENT_DATA)!!

        Log.d(TAG, "$student")
        resultFirstName.text = student.first_name
        resultLastName.text = student.last_name
        resultAge.text = student.age.toString()
        resultGender.text = student.gender
        resultMajor.text = student.major
        resultGpa.text = student.gpa.toString()

    }
}