package com.example.assignment2_studentsapp

import Student
import StudentRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentsapp.databinding.ActivityEditStudentBinding

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private lateinit var selectedStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId = intent.getStringExtra("STUDENT_ID") ?: return

        selectedStudent = StudentRepository.students.find { it.id == studentId } ?: return

        binding.editStudentName.setText(selectedStudent.name)
        binding.editStudentId.setText(selectedStudent.id)

        binding.saveStudentButton.setOnClickListener {
            val newName = binding.editStudentName.text.toString()
            val newId = binding.editStudentId.text.toString()

            if (newName.isNotBlank() && newId.isNotBlank()) {
                selectedStudent.name = newName
                selectedStudent.id = newId
                Toast.makeText(this, "Student updated!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteStudentButton.setOnClickListener {
            StudentRepository.students.remove(selectedStudent)
            Toast.makeText(this, "Student deleted!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}
