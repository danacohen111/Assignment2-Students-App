package com.example.assignment2_studentsapp

import Student
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentsapp.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.studentNameInput.text.toString()
            val id = binding.studentIdInput.text.toString()

            if (name.isNotBlank() && id.isNotBlank()) {
                StudentRepository.students.add(Student(id, name))
                finish()
            }
        }
    }
}
