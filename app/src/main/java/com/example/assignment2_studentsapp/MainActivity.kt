package com.example.assignment2_studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2_studentsapp.adapters.StudentAdapter
import com.example.assignment2_studentsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.addStudentButton.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(StudentRepository.students) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_ID", student.id)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = studentAdapter
    }
    override fun onResume() {
        super.onResume()
        Log.d("StudentRepository", "Students: ${StudentRepository.students}")
        studentAdapter.notifyDataSetChanged()
    }
}
