package com.example.face_recognition_based_attendance_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class ActivitySplashScreen : AppCompatActivity() {

    private var Time_Delay: Long = 2500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.myLooper()!!).postDelayed({

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, Time_Delay)
    }
}