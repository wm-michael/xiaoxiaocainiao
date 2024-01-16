package com.example.module_xlog

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lib_framework.utils.FileUtil
import com.example.lib_xlog.XlogManger
import java.io.File
class XLogMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xlog_main)

        XlogManger.instance.init(this.application)

        val current = (0..10000000).random()
        val message = "this is xlog: $current"
        XlogManger.instance.XLogD("XIAOXIAO", message)

        val SDCARD = this.application.getExternalFilesDir("XLogF").toString()
        val logPath = SDCARD + "/xlog_xiaoxiaocainiao"
        val logfile = File(logPath)
        val files = FileUtil.eachFileRecurse(logfile)

        val tv = findViewById<TextView>(R.id.tv)
        var str = ""
        files.forEach {
            str += it.name + ": " + it.length() + "\n"
        }
        tv.text = str
    }

    override fun onDestroy() {
        super.onDestroy()
        XlogManger.instance.release()
    }
}