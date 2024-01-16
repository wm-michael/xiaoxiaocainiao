package com.example.lib_xlog

import android.app.Application
import com.tencent.mars.BuildConfig
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog

class XlogManger private constructor() {

    private var isInit = false

    companion object {
        val instance: XlogManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            XlogManger()
        }
    }

    //xlog日志文件的加密和解密
    //https://github.com/Tencent/mars/tree/master/mars/xlog/crypt/gen_key.py

    fun init(context: Application) {
        android.util.Log.d("XIAOXIAO", "xlog init 1")
        if (isInit) return
        isInit = true
        android.util.Log.d("XIAOXIAO", "xlog init 2")
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")

        val SDCARD =
            context.getExternalFilesDir("XLogF").toString()
        val logPath = SDCARD + "/xlog_xiaoxiaocainiao"

        val cachePath = context.getCacheDir().toString() + "/xlog_xiaoxiaocainiao"
        android.util.Log.d("XIAOXIAO", "logPath:$logPath")
        android.util.Log.d("XIAOXIAO", "cachePath:$cachePath")
        val xlog = Xlog()
        xlog.setMaxAliveTime(0, 7 * 24 * 60 * 60);
        Log.setLogImp(xlog)
        if (BuildConfig.DEBUG) {
            Log.appenderOpen(
                Xlog.LEVEL_DEBUG,
                Xlog.AppednerModeAsync,
                "",
                logPath,
                "XlogSample",
                0
            )
            Log.setConsoleLogOpen(true)
        } else {
            Log.appenderOpen(
                Xlog.LEVEL_INFO,
                Xlog.AppednerModeAsync,
                cachePath,
                logPath,
                "XlogSample",
                0
            )
            Log.setConsoleLogOpen(false);
        }
    }

    fun release() {
        Log.appenderClose()
    }
}