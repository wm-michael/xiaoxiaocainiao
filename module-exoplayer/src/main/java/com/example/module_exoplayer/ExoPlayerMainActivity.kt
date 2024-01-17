package com.example.module_exoplayer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib_common.adapter.CommonListAdapter
import com.example.module_exoplayer.databinding.ActivityExoPlayerMainBinding
import com.example.module_exoplayer.demo0.Demo1Activity

class ExoPlayerMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExoPlayerMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoPlayerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rv = binding.rv

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(com.example.lib_common.R.drawable.common_list_divider)!!)
        rv.addItemDecoration(itemDecoration)

        val titlelist = arrayOf("basic", "to be continue")
        val adapter = CommonListAdapter(titlelist)
        rv.adapter = adapter
        adapter.setOnItemClickListener(object : CommonListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(baseContext, Demo1Activity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}