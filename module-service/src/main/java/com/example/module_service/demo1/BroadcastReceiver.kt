package com.example.module_service.demo1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val serviceIntent = Intent(p0, LocationForegroundService::class.java)
        p0?.startService(serviceIntent)
    }
}