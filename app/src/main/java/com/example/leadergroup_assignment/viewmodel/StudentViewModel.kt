package com.example.leadergroup_assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leadergroup_assignment.model.StudentDetails
import com.example.leadergroup_assignment.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(private val studentRepository: StudentRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.getStudent()
        }
    }

    val studentList : LiveData<StudentDetails>
        get() = studentRepository.studentList
}