package com.example.leadergroup_assignment.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
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
import java.util.Locale


class StudentAdapter(private val context: Context, val mStudentModel: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(), Filterable {

    private val TAG = StudentAdapter::class.simpleName
    private var myList: ArrayList<Student> = arrayListOf()

    init {
        this.myList = mStudentModel;
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): StudentViewHolder {
        val mStudentListItemBinding =
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.list_student_layout,
                viewGroup, false
            )


        return StudentViewHolder(mStudentListItemBinding)
    }

    override fun onBindViewHolder(mStudentViewHolder: StudentViewHolder, i: Int) {
        val currentStudent = myList!![i]
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
        return if (myList != null) {
            myList!!.size
        } else {
            0
        }
    }


    inner class StudentViewHolder(mStudentListItemBinding: View) :
        RecyclerView.ViewHolder(mStudentListItemBinding) {
        var id: TextView = mStudentListItemBinding.findViewById<TextView>(R.id.serial_no)
        var firstName: TextView = mStudentListItemBinding.findViewById<TextView>(R.id.first_name)
        var lastName: TextView = mStudentListItemBinding.findViewById<TextView>(R.id.last_name)
        var parentLayout: ConstraintLayout =
            mStudentListItemBinding.findViewById<ConstraintLayout>(R.id.parent_layout)
    }


    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                Log.d(TAG, "Performing Filtering $constraint")
                val filterResults = FilterResults()
                if (constraint.isEmpty()) {
                    Log.d(TAG, "Performing Filtering constraint is empty")
                    filterResults.count = mStudentModel.size
                    filterResults.values = mStudentModel
                } else {
                    val resultsModel: MutableList<Student> = ArrayList<Student>()
                    val searchStr = constraint.toString().lowercase(Locale.getDefault())
                    Log.d(TAG, "searchStr = $searchStr")
                    for (student in mStudentModel) {

                        if (student.first_name.lowercase(Locale.getDefault()).contains(searchStr)
                            || student.last_name.lowercase(Locale.getDefault()).contains(searchStr)
                        ) {
                            Log.d(TAG, "Adding Result $student")
                            resultsModel.add(student)
                        }
                        filterResults.count = resultsModel.size
                        filterResults.values = resultsModel
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                myList = results.values as ArrayList<Student>
                notifyDataSetChanged()
            }
        }
    }


}