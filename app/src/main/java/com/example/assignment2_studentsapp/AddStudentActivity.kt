package com.example.assignment2_studentsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentsapp.databinding.ActivityAddStudentBinding
import Student

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.studentNameInput.text.toString()
            val id = binding.studentIdInput.text.toString()
            val phone = binding.studentPhoneInput.text.toString()
            val address = binding.studentAddressInput.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                val newStudent = Student(
                    id = id,
                    name = name,
                    phone = phone,
                    address = address,
                    isChecked = false
                )
                StudentRepository.students.add(newStudent)
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}
