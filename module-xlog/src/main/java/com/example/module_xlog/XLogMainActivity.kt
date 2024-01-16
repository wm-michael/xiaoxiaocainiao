package com.example.module_xlog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lib_xlog.XlogManger

class XLogMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xlog_main)

        XlogManger.instance.init(this.application)

        Log.d("XIAOXIAO", "this is xlog")
    }

    override fun onDestroy() {
        super.onDestroy()
        XlogManger.instance.release()
    }
}