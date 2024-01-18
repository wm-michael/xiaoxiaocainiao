package com.example.module_file_system.appspecific

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.module_file_system.databinding.ActivityInternalstorage0Binding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Internalstorage0Activity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityInternalstorage0Binding
    private var resultStr =  ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInternalstorage0Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        viewBinding.createFile.setOnClickListener { storeFileUsingStream() }
        viewBinding.deleteFile.setOnClickListener { deleteFile() }

        viewBinding.readFile.setOnClickListener { listFiles() }

        viewBinding.createDir.setOnClickListener { createDir() }

        viewBinding.createDirInFile.setOnClickListener { createDirInFiles() }


        listFiles()
    }

    private fun showTextNewLine(txt: String) {
        resultStr += txt + "\n"
        viewBinding.tv.text = resultStr
    }

    private fun initText() {
        resultStr =  ""
        viewBinding.tv.text = resultStr
        showTextNewLine("let's go")
        showTextNewLine("")
        showTextNewLine("/data/data 目录实际软连接到 /data/user/0 目录, /data/user/0只有ROOT以后才可以看到")
        showTextNewLine("")
        val file_file = this.filesDir
        showTextNewLine(file_file.absolutePath)
        val file_cache = this.cacheDir
        showTextNewLine(file_cache.absolutePath)
    }

    private fun deleteFile() {
        var count = 0
        var files: Array<String> = this.fileList()
        files.forEach {
            if(it.startsWith("myfile-")) {
                deleteFile(it)
                count++
            }
        }
        showTextNewLine("删除文件: ${count}")
    }

    private fun createDir() {
        val currentDate = getCurrentTime()
        val dirname = "dirName_$currentDate"
        this.getDir("dirName-$currentDate", Context.MODE_PRIVATE)
        showTextNewLine("生成文件夹: $dirname ,注意了这个文件夹和this.filesDir, this.cacheDir同级")
        showTextNewLine("getDir(String dirName, int mode) 获取/data/data/包名/dirName目录 (有则打开，没有则新建)")
    }

    private fun storeFileUsingStream(){
        val currentDate = getCurrentTime()
        val filename = "myfile-" + currentDate + ".txt"
        val fileContents = "Hello world!"
        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
        showTextNewLine("生成文件: $filename")
    }

    private fun listFiles() {
        initText()
        var files: Array<String> = this.fileList()
        files.forEach {
            showTextNewLine("---- $it")
        }
    }

    private fun createDirInFiles() {
        val dir: File = File(this.filesDir.absolutePath + File.separator + "subdir")
        if (dir.exists()) {
            showTextNewLine("文件夹已经存在: ${dir.absolutePath}")
            if(dir.deleteRecursively()) {
                showTextNewLine("删除文件夹成功: ${dir.absolutePath}")
            } else {
                showTextNewLine("删除文件夹失败: ${dir.absolutePath}")
            }
        } else {
            if(dir.mkdirs()) {
                showTextNewLine("创建文件夹成功: ${dir.absolutePath}")
            } else {
                showTextNewLine("创建文件夹失败: ${dir.absolutePath}")
            }
        }
    }

    private fun getCurrentTime(): String {
        val date = Date()
        val dateFormatWithZone =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val currentDate = dateFormatWithZone.format(date)
        return currentDate
    }
}