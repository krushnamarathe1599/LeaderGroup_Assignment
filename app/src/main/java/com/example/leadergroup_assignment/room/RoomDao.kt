package com.example.leadergroup_assignment.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.leadergroup_assignment.model.Student


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: List<Student>)

    @Query("SELECT * FROM student")
    fun getStudent(): List<Student>
}