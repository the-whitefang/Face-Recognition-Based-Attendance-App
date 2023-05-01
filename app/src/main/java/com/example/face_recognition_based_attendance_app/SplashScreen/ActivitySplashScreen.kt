package com.example.face_recognition_based_attendance_app.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.face_recognition_based_attendance_app.Welcome.MainActivity
import com.example.face_recognition_based_attendance_app.R
import com.example.face_recognition_based_attendance_app.databinding.ActivitySplashScreenBinding

class ActivitySplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    var i =0
    var progressbar: ProgressBar?=null
    lateinit var progresstext: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.
        setContentView(this,R.layout.activity_splash_screen)

        progresstext = binding.progressText
        progressbar = binding.progressbar

        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                if (i<=100){
                    progresstext.setText(""+i)
                    progressbar?.setProgress(i)
                    i++
                    handler.postDelayed(this,50)
                }
                else{
                    handler.removeCallbacks(this)
                    val intent = Intent(this@ActivitySplashScreen, MainActivity::class.java)
                    startActivity(intent)
                }


            }

        },50)


    }
}