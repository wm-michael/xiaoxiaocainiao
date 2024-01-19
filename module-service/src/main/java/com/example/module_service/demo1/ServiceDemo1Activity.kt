package com.example.module_service.demo1

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.module_service.databinding.ActivityServiceDemo1Binding

class ServiceDemo1Activity : AppCompatActivity() {

    private val BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 456
    private val LOCATION_PERMISSION_REQUEST_CODE = 123

    private lateinit var viewBding: ActivityServiceDemo1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBding = ActivityServiceDemo1Binding.inflate(layoutInflater)
        setContentView(viewBding.root)

        viewBding.buttonStartService.setOnClickListener {
            Log.d("XIAOXIAO", "buttonStart CLICK")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                checkNotificationPermission()
            } else {
                checkAndRequestLocationPermissions()
            }
        }

        viewBding.buttonStopService.setOnClickListener {
            stopLocationService()
        }

        val receiver = ComponentName(this, BootReceiver::class.java)
        packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

    }

    private fun startLocationService() {
        val serviceIntent = Intent(this, LocationForegroundService::class.java)
        startService(serviceIntent)
    }

    private fun stopLocationService() {
        val serviceIntent = Intent(this, LocationForegroundService::class.java)
        stopService(serviceIntent)
    }

    private fun checkBackGroundLocationPermission(): Boolean {
        Log.d("XIAOXIAO", "请求BackGround定位权限")
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        } else {
            return true
        }

    }

    private fun requestBackGroundLocationPermission() {
        Log.d("XIAOXIAO", "请求BackGround定位权限")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    /*
    请求用户权限,得到的结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    startLocationService()
                } else {
                    // Handle the case where the user denies the foreground service permission
                }
            }

            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                    checkAndRequestLocationPermissions()
                } else {
                    // Handle the case where the user denies the location permission
                }
            }
        }
    }

    /*
    检测定位权限
     */
    private fun checkAndRequestLocationPermissions() {
        Log.d("XIAOXIAO", "请求定位权限1")
        if (checkLocationPermission()) {
            Log.d("XIAOXIAO", "请求定位权限1 - GOOD")
            if (checkBackGroundLocationPermission()) {
                startLocationService()
            } else {
                requestBackGroundLocationPermission()
            }

        } else {
            Log.d("XIAOXIAO", "请求定位权限1 - BAD")
            requestLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestLocationPermission() {
        Log.d("XIAOXIAO", "要求给与定位权限 - BAD")
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    /**
     * 通知权限
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkNotificationPermission() {
        val permission = android.Manifest.permission.POST_NOTIFICATIONS
        when {
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                // make your action here
                checkAndRequestLocationPermissions()
            }

            shouldShowRequestPermissionRationale(permission) -> {
                // permission denied permanently
            }

            else -> {
                requestNotificationPermission.launch(permission)
            }
        }
    }

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) // make your action here
                checkAndRequestLocationPermissions()
        }
}