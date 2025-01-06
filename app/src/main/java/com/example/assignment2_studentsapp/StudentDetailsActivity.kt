package com.example.assignment2_studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentsapp.databinding.ActivityStudentDetailsBinding
import Student

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId = intent.getStringExtra("STUDENT_ID")
        student = StudentRepository.students.find { it.id == studentId }

        student?.let { displayStudentDetails(it) }

        binding.editButton.setOnClickListener {
            startActivity(Intent(this, EditStudentActivity::class.java).apply {
                putExtra("STUDENT_ID", student?.id)
            })
        }

        binding.cancelButton.setOnClickListener { finish() }
    }

    private fun displayStudentDetails(student: Student) {
        binding.apply {
            studentPic.setImageResource(R.drawable.student_pic)
            studentName.text = "Name: ${student.name}"
            studentId.text = "ID: ${student.id}"
            studentPhone.text = "Phone: ${student.phone}"
            studentAddress.text = "Address: ${student.address}"
            isChecked.text = if (student.isChecked) "Checked: Yes" else "Checked: No"
        }
    }

    override fun onResume() {
        super.onResume()
        loadStudentDetails(intent.getStringExtra("STUDENT_ID"))
    }

    private fun loadStudentDetails(studentId: String?) {
        student = StudentRepository.students.find { it.id == studentId }
        student?.let { displayStudentDetails(it) }
    }
}