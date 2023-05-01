package com.example.face_recognition_based_attendance_app.Dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.ImageReader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.provider.MediaStore
import android.view.MenuItem
import android.view.Surface
import android.view.TextureView
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
import java.io.File
import java.io.FileOutputStream

class ActivityMainDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityMainDashboardBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    private var our_request_code:Int = 123

    lateinit var handler: Handler
    lateinit var handlerThread: HandlerThread
    lateinit var cameraManager: CameraManager
    lateinit var cameraCaptureSession: CameraCaptureSession
    lateinit var cameraDevice: CameraDevice
    lateinit var captureRequest: CaptureRequest
    lateinit var textureView: TextureView
    lateinit var capReq: CaptureRequest.Builder
    lateinit var imageReader: ImageReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_dashboard)


        get_permisions()

        textureView = binding.textureView
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        handlerThread = HandlerThread("videoThread")
        handlerThread.start()
        handler = Handler((handlerThread).looper)
        textureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {

            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {

                open_camera()

            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {

            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

            }
        }

        imageReader = ImageReader.newInstance(1080,1920, ImageFormat.JPEG,1)
        imageReader.setOnImageAvailableListener(object: ImageReader.OnImageAvailableListener{
            override fun onImageAvailable(reader: ImageReader?) {

                var image = reader?.acquireLatestImage()
                var buffer = image!!.planes[0].buffer
                var bytes = ByteArray(buffer.remaining())
                buffer.get(bytes)

                var file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"img.jpeg")
                var opStream = FileOutputStream(file)

                opStream.write(bytes)
                opStream.close()

                image?.close()
                Toast.makeText(this@ActivityMainDashboard,
                    "image captured",Toast.LENGTH_SHORT).show()
            }
        },handler)

        binding.btnScan.apply {
            setOnClickListener {
                capReq = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
                capReq.addTarget(imageReader.surface)
                cameraCaptureSession.capture(capReq.build(),null,null)
            }
        }





        drawerLayout = binding.Drawerlayout
        val navView: NavigationView = binding.menu

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.menu.setNavigationItemSelectedListener {

            it.isChecked = true
            when(it.itemId){

                R.id.attendancerecord -> Toast.makeText(applicationContext,"Clicked Attendance Record",Toast.LENGTH_SHORT).show()
                R.id.addface -> Toast.makeText(applicationContext,"Clicked Add Face",Toast.LENGTH_SHORT).show()
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









    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraDevice.close()
        handler.removeCallbacksAndMessages(null)
        handlerThread.quitSafely()
    }

    @SuppressLint("MissingPermission")
    fun open_camera(){
        cameraManager.openCamera(cameraManager.cameraIdList[0], object : CameraDevice.StateCallback(){

            override fun onOpened(camera: CameraDevice) {
                cameraDevice = camera
                capReq = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                var surface = Surface(textureView.surfaceTexture)
                capReq.addTarget(surface)

                cameraDevice.createCaptureSession(listOf(surface,imageReader.surface),object:
                    CameraCaptureSession.StateCallback() {
                    override fun onConfigured(session: CameraCaptureSession) {
                        cameraCaptureSession = session
                        cameraCaptureSession.setRepeatingRequest(capReq.build(),null,
                            null)
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {

                    }
                },handler)
            }

            override fun onDisconnected(camera: CameraDevice) {

            }

            override fun onError(camera: CameraDevice, error: Int) {

            }
        },handler)
    }

    fun get_permisions(){
        var permissionlst = mutableListOf<String>()

        if(checkSelfPermission(android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) permissionlst.
        add(android.Manifest.permission.CAMERA)

        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) permissionlst.
        add(android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) permissionlst.
        add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissionlst.size > 0){
            requestPermissions(permissionlst.toTypedArray(),101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        grantResults.forEach {
            if (it != PackageManager.PERMISSION_GRANTED){
                get_permisions()
            }
        }
    }



}