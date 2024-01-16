package com.example.lib_framework.utils

import android.util.Log
import java.io.File

object FileUtil {
    //指定目录的所有文件
    fun eachFileRecurse(inputFile: File): Array<File> {
        var result = ArrayList<File>()
        val files = inputFile.listFiles() ?: return result.toTypedArray()
        for (file in files) {
            if (file.isDirectory) {
                //eachFileRecurse(file,operation)
            } else {
                Log.d("XIAOXIAO", file.absolutePath)
                result.add(file)
            }
        }
        return result.toTypedArray()
    }

}