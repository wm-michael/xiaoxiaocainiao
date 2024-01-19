package com.example.module_service.demo0

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.module_service.databinding.ActivityServiceDemo0Binding


class ServiceDemo0Activity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityServiceDemo0Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityServiceDemo0Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonStartService.setOnClickListener {
            if(!checkPermission()) {
                return@setOnClickListener
            }
            startService()
        }

        viewBinding.buttonStopService.setOnClickListener {
            stopService()
        }
    }

    fun startService() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
    }

    private fun checkPermission(): Boolean {
        val checker = com.example.lib_common.permission.CheckNotifyPermissionUtils()
        if (checker.checkNotifyPermission(this)) {
            Toast.makeText(this,"有权限", Toast.LENGTH_SHORT)
            return true
        } else {
            Toast.makeText(this,"没有权限", Toast.LENGTH_SHORT)
            checker.tryJumpNotifyPage(this)
        }
        return false
    }
}