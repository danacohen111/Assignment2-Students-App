package com.example.assignment2_studentsapp

import Student
import StudentRepository
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentsapp.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private lateinit var selectedStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId = intent.getStringExtra("STUDENT_ID") ?: return

        selectedStudent = StudentRepository.students.find { it.id == studentId } ?: return

        binding.studentName.text = selectedStudent.name
        binding.studentId.text = selectedStudent.id
        binding.studentImage.setImageResource(R.drawable.student_pic)

        binding.editStudentButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", selectedStudent.id)
            startActivity(intent)
        }
    }
}
