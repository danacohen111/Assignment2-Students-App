package com.example.assignment2_studentsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_studentsapp.databinding.ActivityEditStudentBinding
import Student
import android.content.Intent

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId = intent.getStringExtra("STUDENT_ID")
        student = StudentRepository.students.find { it.id == studentId }

        student?.let {
            binding.studentNameInput.setText(it.name)
            binding.studentIdInput.setText(it.id)
            binding.studentPhoneInput.setText(it.phone)
            binding.studentAddressInput.setText(it.address)
        }

        binding.saveButton.setOnClickListener {
            val name = binding.studentNameInput.text.toString()
            val id = binding.studentIdInput.text.toString()
            val phone = binding.studentPhoneInput.text.toString()
            val address = binding.studentAddressInput.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                student?.let {
                    it.name = name
                    it.id = id
                    it.phone = phone
                    it.address = address
                    Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }

        binding.deleteButton.setOnClickListener {
            StudentRepository.students.remove(student)
            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
