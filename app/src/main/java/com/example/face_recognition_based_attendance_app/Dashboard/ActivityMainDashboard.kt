package com.example.face_recognition_based_attendance_app.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.face_recognition_based_attendance_app.databinding.ActivityMainDashboardBinding

class ActivityMainDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityMainDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}