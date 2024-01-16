package com.example.module_localstore

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.lib_localstore.LocalStoreManger
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivityLocalStore : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private val nameLiveData = MutableLiveData<String>("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_local_store)

        LocalStoreManger.instance.init(this.applicationContext)

        mainViewModel = MainViewModelFactory("XIAOXIAO").create(MainViewModel::class.java)

        nameLiveData.observe(this) {
            Log.d("XIAOXIAO", "observe name: $it")
            findViewById<TextView>(R.id.tv).text = it
        }

        findViewById<Button>(R.id.bt_save).setOnClickListener {
            val save_str = findViewById<EditText>(R.id.et).text.toString()
            Log.d("XIAOXIAO", "save btn click: $save_str")
            mainViewModel.saveNanme(save_str)
        }

        lifecycleScope.launch {
            mainViewModel.nameFlow.collectLatest {
                Log.d("XIAOXIAO", "read btn click: $it")
                nameLiveData.value = it
            }
        }
    }
}