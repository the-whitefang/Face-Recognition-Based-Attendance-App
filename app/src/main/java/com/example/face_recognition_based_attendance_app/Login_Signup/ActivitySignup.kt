package com.example.face_recognition_based_attendance_app.Login_Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.face_recognition_based_attendance_app.databinding.ActivityLoginBinding
import com.example.face_recognition_based_attendance_app.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class ActivitySignup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.subtn.setOnClickListener {
            val email = binding.suusername.text.toString()
            val password = binding.supassword.text.toString()
            val confirmPass = binding.suconfirmpwd.text.toString()



            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()){
                if (password == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this,ActivityLoginBinding::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Incorrect Password !!",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed !!",Toast.LENGTH_SHORT).show()
            }
        }


    }
}