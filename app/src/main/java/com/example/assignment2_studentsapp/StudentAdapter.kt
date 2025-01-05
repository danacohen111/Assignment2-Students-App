package com.example.assignment2_studentsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_studentsapp.R
import Student

class StudentAdapter(
    private val students: List<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentImage: ImageView = itemView.findViewById(R.id.studentImage)
        val studentName: TextView = itemView.findViewById(R.id.studentName)
        val studentId: TextView = itemView.findViewById(R.id.studentId)
        val studentCheck: CheckBox = itemView.findViewById(R.id.studentCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.studentImage.setImageResource(R.drawable.student_pic)

        holder.studentName.text = student.name
        holder.studentId.text = student.id
        holder.studentCheck.isChecked = student.isChecked

        holder.studentCheck.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        holder.itemView.setOnClickListener {
            onItemClick(student)
        }
    }

    override fun getItemCount(): Int = students.size
}
