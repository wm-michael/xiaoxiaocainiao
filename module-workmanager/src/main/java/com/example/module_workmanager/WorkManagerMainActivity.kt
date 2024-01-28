package com.example.module_workmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_workmanager.databinding.ActivityWorkManagerMainBinding
import com.example.module_workmanager.demo0.WorkManager0Activity
import com.example.module_workmanager.demo1.WorkManager1Activity
import com.example.module_workmanager.demo2.WorkManager2Activity

class WorkManagerMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.layoutManager = layoutManager

        val examples = arrayOf("once alert", "upload userinfo","download image, blur image")
        val adapter = CustomAdapter(examples)
        adapter.setOnItemClickListener { _, position ->
            when (position) {
                0 -> {
                    val intent = Intent(
                        baseContext,
                        WorkManager0Activity::class.java
                    )
                    startActivity(intent)
                }

                1 -> {
                    val intent = Intent(
                        baseContext,
                        WorkManager1Activity::class.java
                    )
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(
                        baseContext,
                        WorkManager2Activity::class.java
                    )
                    startActivity(intent)
                }
            }
        }
        binding.rv.adapter = adapter
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.work_manager_divider)!!)
        binding.rv.addItemDecoration(itemDecoration)
    }
}