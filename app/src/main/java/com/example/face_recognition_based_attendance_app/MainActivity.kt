package com.example.face_recognition_based_attendance_app


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.face_recognition_based_attendance_app.Login_Signup.ActivityLogin
import com.example.face_recognition_based_attendance_app.Login_Signup.ActivitySignup


class MainActivity : AppCompatActivity() {
    private lateinit var logininbtn: Button
    private lateinit var Signupbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logininbtn = findViewById(R.id.loginbtn)
        Signupbtn = findViewById(R.id.signupbtn)

      logininbtn.setOnClickListener {
          val intent = Intent(this,ActivityLogin::class.java)
          startActivity(intent)
      }

        Signupbtn.setOnClickListener {
            val intent = Intent(this,ActivitySignup::class.java)
            startActivity(intent)
        }
    }
}