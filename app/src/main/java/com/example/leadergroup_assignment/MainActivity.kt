package com.example.leadergroup_assignment

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadergroup_assignment.adapter.StudentAdapter
import com.example.leadergroup_assignment.model.Student
import com.example.leadergroup_assignment.viewmodel.StudentViewModel
import com.example.leadergroup_assignment.viewmodel.StudentViewModelFactory


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName
    private lateinit var studentViewModel: StudentViewModel

    private lateinit var searchView: SearchView

    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search_bar)


        val repository = (application as MyApplication).studentRepository

        val viewModelFactory = StudentViewModelFactory(repository)

        studentViewModel =
            ViewModelProvider(this, viewModelFactory).get(StudentViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        studentViewModel.studentList.observe(this) {
            studentAdapter = StudentAdapter(this, it.students as ArrayList<Student>)
            recyclerView.adapter = studentAdapter
        }





        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.e(TAG, " data search$newText")
                studentAdapter.filter?.filter(newText)
                return true
            }
        })
    }


}