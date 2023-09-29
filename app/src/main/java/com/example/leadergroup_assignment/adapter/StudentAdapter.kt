package com.example.leadergroup_assignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.leadergroup_assignment.Constants.Companion.STUDENT_DATA
import com.example.leadergroup_assignment.DetailsActivity
import com.example.leadergroup_assignment.R
import com.example.leadergroup_assignment.model.Student

class StudentAdapter (private val context:Context):
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var mStudentModel: List<Student>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): StudentViewHolder {
        val mStudentListItemBinding =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_student_layout,
            viewGroup, false)


        return StudentViewHolder(mStudentListItemBinding)
    }

    override fun onBindViewHolder(mStudentViewHolder: StudentViewHolder, i: Int) {
        val currentStudent = mStudentModel!![i]
        mStudentViewHolder.id.text = currentStudent.id.toString()
        mStudentViewHolder.firstName.text = currentStudent.first_name
        mStudentViewHolder.lastName.text = currentStudent.last_name
        mStudentViewHolder.parentLayout.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(STUDENT_DATA, currentStudent)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return if (mStudentModel != null) {
            mStudentModel!!.size
        } else {
            0
        }
    }

    fun setDeveloperList(mStudentModel: List<Student>) {
        this.mStudentModel = mStudentModel
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(var mStudentListItemBinding: View) :
        RecyclerView.ViewHolder(mStudentListItemBinding){
            var id = mStudentListItemBinding.findViewById<TextView>(R.id.serial_no)
        var firstName = mStudentListItemBinding.findViewById<TextView>(R.id.first_name)
        var lastName = mStudentListItemBinding.findViewById<TextView>(R.id.last_name)
        var parentLayout = mStudentListItemBinding.findViewById<ConstraintLayout>(R.id.parent_layout)
    }



}