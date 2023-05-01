package com.example.face_recognition_based_attendance_app.Dashboard

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.face_recognition_based_attendance_app.R
import com.example.face_recognition_based_attendance_app.databinding.ActivityMainDashboardBinding
import com.google.android.material.navigation.NavigationView

class ActivityMainDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityMainDashboardBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    private var our_request_code:Int = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_dashboard)

        drawerLayout = binding.Drawerlayout
        val navView: NavigationView = binding.menu

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.menu.setNavigationItemSelectedListener {

            it.isChecked = true
            when(it.itemId){

                R.id.attendancerecord -> replaceFragment(Attendance_Record(), it.title.toString())
                R.id.addface -> replaceFragment(Add_Face(),it.title.toString())
                R.id.calender -> Toast.makeText(applicationContext,"Clicked Calender",Toast.LENGTH_SHORT).show()
                R.id.setting -> Toast.makeText(applicationContext,"Clicked Settings",Toast.LENGTH_SHORT).show()
                R.id.Login -> Toast.makeText(applicationContext,"Clicked Login",Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(applicationContext,"Clicked About",Toast.LENGTH_SHORT).show()
                R.id.help -> Toast.makeText(applicationContext,"Clicked Help",Toast.LENGTH_SHORT).show()
            }
            true
        }



        binding.btnScan.setOnClickListener {
            Toast.makeText(this,"Scanned Successfully !!",Toast.LENGTH_SHORT).show()
        }



    }

    private fun replaceFragment(fragment: Fragment, title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }







    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}