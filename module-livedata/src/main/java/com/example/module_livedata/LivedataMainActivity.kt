package com.example.module_livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.module_livedata.databinding.ActivityLivedataMainBinding

class LivedataMainActivity : AppCompatActivity() {

    // Obtain ViewModel
    private val viewmodel: LiveDataViewModel by viewModels { LiveDataVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain binding object using the Data Binding library
        val binding = DataBindingUtil.setContentView<ActivityLivedataMainBinding>(
            this, R.layout.activity_livedata_main
        )

        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this

        // Bind ViewModel
        binding.viewmodel = viewmodel
    }
}