package com.example.leadergroup_assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.leadergroup_assignment.repository.StudentRepository

class StudentViewModelFactory(private val studentRepository: StudentRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(studentRepository) as T
    }
}