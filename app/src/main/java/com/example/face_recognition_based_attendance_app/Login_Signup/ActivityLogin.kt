package com.example.face_recognition_based_attendance_app.Login_Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.face_recognition_based_attendance_app.Dashboard.ActivityMainDashboard
import com.example.face_recognition_based_attendance_app.databinding.ActivityLoginBinding
import com.example.face_recognition_based_attendance_app.databinding.ActivityMainDashboardBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.sibtn.setOnClickListener {
            val email = binding.siusername.text.toString()
            val password = binding.siuserpassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() ){

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, ActivityMainDashboard::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }

                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tag.setOnClickListener {
            val intent = Intent(this,ActivitySignup::class.java)
            startActivity(intent)
        }
        }


    }
