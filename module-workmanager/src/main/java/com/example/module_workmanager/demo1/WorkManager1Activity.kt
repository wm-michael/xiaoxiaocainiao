package com.example.module_workmanager.demo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.module_workmanager.databinding.ActivityWorkManager1Binding
import java.util.concurrent.TimeUnit

class WorkManager1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManager1Binding

    private var workRequestLiveData: LiveData<WorkInfo> = MutableLiveData(null)
    private var resulsStr = "testing"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManager1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        uploadPlan()
    }

    fun uploadPlan() {

        val instanceWorkManager = WorkManager.getInstance(this)

        /*
        demo1
         */
        /*
        val uploadWorkRequest = OneTimeWorkRequestBuilder<UserDataUploadWorker>().build()
        instanceWorkManager.enqueue(uploadWorkRequest)
         */

        /**
         * demo1
         */
        /*
        val uploadDataConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val uploadWorkRequest = OneTimeWorkRequestBuilder<UserDataUploadWorker>().setConstraints(uploadDataConstraints).build()
        instanceWorkManager.enqueue(uploadWorkRequest)
         */

        /*
        * Constraints 复杂demo
        * */
        //val constraints = Constraints.Builder()
        //        .setRequiresBatteryNotLow(true)
        //        .setRequiredNetworkType(NetworkType.CONNECTED)
        //        .setRequiresCharging(true)
        //        .setRequiresStorageNotLow(true)
        //        .setRequiresDeviceIdle(true)
        //        .build()


        val userDataJson = "{name:\"张三\", age:\"28\"}"
        val someOtherData = false
        val uploadDataConstraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val inputData = Data.Builder()
            .putString("user_data", userDataJson)
            .putBoolean("other_data", someOtherData)
            .build()
        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<UserDataUploadWorker>(
                15,
                TimeUnit.MINUTES
            ).addTag("XIAOXIAOCAINIAO" + System.currentTimeMillis()).setBackoffCriteria(
                BackoffPolicy.LINEAR,
                PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
                TimeUnit.SECONDS
            ).setConstraints(uploadDataConstraints).setInputData(inputData).build()


        //这样做不行，因为你不知道什么时候真正执行work manager
        /*
        val workInfo = WorkManager.getInstance().getWorkInfoById(periodicWorkRequest.id).get()
        val wasSuccess = workInfo.outputData.getBoolean("is_success", false)
         */
        workRequestLiveData = instanceWorkManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
        workRequestLiveData.observe(this) { workInfo ->
            resulsStr += "\n"
            resulsStr += workInfo.outputData.getBoolean("is_success", false).toString()
            binding.tv.text = resulsStr
        }

        Log.d("XIAOXIAO", "Let's go")
        instanceWorkManager.enqueue(periodicWorkRequest)
    }
}