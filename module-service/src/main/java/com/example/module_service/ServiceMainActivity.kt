package com.example.module_service

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib_common.adapter.CommonListAdapter
import com.example.module_service.databinding.ActivityServiceMainBinding
import com.example.module_service.demo0.ServiceDemo0Activity

class ServiceMainActivity : AppCompatActivity() {

    private lateinit var viewBingding: ActivityServiceMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBingding = ActivityServiceMainBinding.inflate(layoutInflater)
        setContentView(viewBingding.root)

        val titles = arrayOf("ForegroundService", "other")

        val adapter = CommonListAdapter(titles)
        adapter.setOnItemClickListener(object : CommonListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(baseContext, ServiceDemo0Activity::class.java)
                        startActivity(intent)
                    }
                }
            }

        })

        viewBingding.rv.adapter = adapter
        val layout = LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        viewBingding.rv.layoutManager = layout

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(com.example.lib_common.R.drawable.common_list_divider)!!)
        viewBingding.rv.addItemDecoration(itemDecoration)
    }
}