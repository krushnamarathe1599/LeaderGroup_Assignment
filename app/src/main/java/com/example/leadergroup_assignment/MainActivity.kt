package com.example.leadergroup_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadergroup_assignment.adapter.StudentAdapter
import com.example.leadergroup_assignment.model.Student
import com.example.leadergroup_assignment.viewmodel.StudentViewModel
import com.example.leadergroup_assignment.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var studentViewModel: StudentViewModel

    private lateinit var searchView:EditText

    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search_bar)


        val repository = (application as MyApplication).studentRepository

        val viewModelFactory = StudentViewModelFactory(repository)

        studentViewModel = ViewModelProvider(this,viewModelFactory).get(StudentViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        //init the Custom adataper
        studentAdapter = StudentAdapter(this)
        //set the CustomAdapter
        recyclerView.adapter = studentAdapter
        studentViewModel.studentList.observe(this){
            studentAdapter.setDeveloperList(it.students)

        }
    }


}