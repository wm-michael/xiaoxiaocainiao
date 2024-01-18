package com.example.module_file_system.appspecific

import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.module_file_system.databinding.ActivityExternalStorage0MainBinding
import java.io.File

class ExternalStorage0MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityExternalStorage0MainBinding
    private var resultStr =  ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityExternalStorage0MainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        initText()
    }

    private fun initText() {
        resultStr =  ""
        viewBinding.tv.text = resultStr
        showTextNewLine("let's go")
        showTextNewLine("")
        val available = Environment.getExternalStorageState()
        showTextNewLine("external available: $available . MEDIA_MOUNTED是可读可写,MEDIA_MOUNTED_READ_ONLY是只读")
        showTextNewLine("")
        showFolders()
        showTextNewLine("/sdcard/Android/ 实际软连接到 /storage/emuated/o/Android/")
        showTextNewLine("")
        val appSpecificExternalDir = this.getExternalFilesDir(null)
        showTextNewLine("context.getExternalFilesDir: $appSpecificExternalDir")
        showTextNewLine("")
        val externalCacheFile = this.externalCacheDir
        showTextNewLine("context.externalCacheDir: $externalCacheFile")
        showTextNewLine("")
        showTextNewLine("media files that provide value to the user only within your app:  ")
        val file = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        showTextNewLine("DIRECTORY_PICTURES: ${file?.absolutePath}")

        showTextNewLine("系统目录 Pictures::  ")
        showTextNewLine("/sdcard/Pictures/ 实际软连接到 /storage/emuated/o/Pictures/")
        val systemPicFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        showTextNewLine("SYSTEM DIRECTORY_PICTURES: ${systemPicFile?.absolutePath}")

        showTextNewLine("系统目录 Download::  ")
        val systemDownloadFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        showTextNewLine("SYSTEM DIRECTORY_PICTURES: ${systemDownloadFile?.absolutePath}")

        showTextNewLine("系统目录 sdcard根目录::  ")
        val systemSdcardFile = Environment.getExternalStorageDirectory()
        showTextNewLine("SYSTEM DIRECTORY_PICTURES: ${systemSdcardFile?.absolutePath}")
    }

    private fun showTextNewLine(txt: String) {
        resultStr += txt + "\n"
        viewBinding.tv.text = resultStr
    }

    private fun showFolders() {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        showTextNewLine("不一定只有一个外部存储,特别是老机器. size: ${externalStorageVolumes.size}")
        externalStorageVolumes.forEach {
            showTextNewLine("---: ${it.absolutePath}")
        }
    }
}