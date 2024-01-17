package com.example.module_workmanager.demo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class UserDataUploadWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {

        val userDataJson = inputData.getString("user_data")
        val someOtherData= inputData.getBoolean("other_data", false) // pass false as default value
        Log.d("XIAOXIAO", "doWork: userDataJson = $userDataJson, someOtherData = $someOtherData")
        val uploadresult = uploadUserData()


        val outputData = workDataOf("is_success" to uploadresult)

        return Result.success(outputData)
    }

    private fun uploadUserData(): Boolean {
        // do upload work here
        val random = (0..10).random()
        Log.d("XIAOXIAO", "UserDataUploadWorker - uploadUserData: $random")
        return random % 2 == 0
    }
}