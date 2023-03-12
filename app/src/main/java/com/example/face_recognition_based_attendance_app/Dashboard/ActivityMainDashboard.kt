package com.example.face_recognition_based_attendance_app.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.face_recognition_based_attendance_app.R
import com.example.face_recognition_based_attendance_app.databinding.ActivityMainDashboardBinding
import com.google.android.material.navigation.NavigationView

class ActivityMainDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityMainDashboardBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_dashboard)

        val drawerLayout:DrawerLayout = binding.Drawerlayout
        val navView: NavigationView = binding.menu

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.menu.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.home -> Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show()
                R.id.calender -> Toast.makeText(applicationContext,"Clicked Calender",Toast.LENGTH_SHORT).show()
                R.id.setting -> Toast.makeText(applicationContext,"Clicked Settings",Toast.LENGTH_SHORT).show()
                R.id.Login -> Toast.makeText(applicationContext,"Clicked Login",Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(applicationContext,"Clicked About",Toast.LENGTH_SHORT).show()
                R.id.help -> Toast.makeText(applicationContext,"Clicked Help",Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}