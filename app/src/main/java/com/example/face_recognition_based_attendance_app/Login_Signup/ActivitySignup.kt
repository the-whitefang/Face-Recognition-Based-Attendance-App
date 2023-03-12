package com.example.face_recognition_based_attendance_app.Login_Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.face_recognition_based_attendance_app.databinding.ActivityLoginBinding
import com.example.face_recognition_based_attendance_app.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivitySignup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var userUID : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.subtn.setOnClickListener {
            val name = binding.suusername.text.toString()
            val email = binding.sumail.text.toString()
            val password = binding.supassword.text.toString()
            val confirmPass = binding.suconfirmpwd.text.toString()



            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()){
                if (password == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener{
                        if (true){
                            Toast.makeText(this,"Your Registration was Successful !!",
                            Toast.LENGTH_SHORT).show()
                            userUID = it.user?.uid.toString()
                            database = FirebaseDatabase.getInstance().getReference("Users")
                            val User = User(name,email,password,confirmPass)
                            database.child(userUID).setValue(User).addOnSuccessListener {
                                binding.sumail.text.clear()
                                binding.suusername.text.clear()
                                binding.supassword.text.clear()
                                binding.suconfirmpwd.text.clear()

                                Toast.makeText(this,"Sucessfully saved !!",Toast.LENGTH_SHORT).show()
                            }.addOnFailureListener {
                                Toast.makeText(this,"Falied.",Toast.LENGTH_SHORT).show()
                            }



                            val intent = Intent(this,ActivityLogin::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.credential.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this,"not working",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Incorrect Password !!",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed !!",Toast.LENGTH_SHORT).show()
            }


        }

        binding.tag2.setOnClickListener {
            val intent = Intent(this,ActivityLogin::class.java)
            startActivity(intent)
        }


    }
}