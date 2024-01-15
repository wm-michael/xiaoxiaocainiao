package com.example.module_apollographql

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lib_apollographql.ApolloCountryClient
import kotlinx.coroutines.launch

class ApollographqlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apollographql)


        val tv = findViewById<TextView>(R.id.textview)
        tv.text = "loading......"
        lifecycleScope.launch {
            val data = ApolloCountryClient().getCountries()
            Log.d("XIAOXIAO", "获取数据: " + data.toString())
            tv.text = data.toString()
        }
    }
}