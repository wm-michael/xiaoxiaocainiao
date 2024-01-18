package com.example.module_file_system

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib_common.adapter.CommonListAdapter
import com.example.module_file_system.appspecific.ExternalStorage0MainActivity
import com.example.module_file_system.appspecific.Internalstorage0Activity
import com.example.module_file_system.databinding.ActivityFileSystemMainBinding

class FileSystemMainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityFileSystemMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFileSystemMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewBinding.rv.layoutManager = layoutManager

        val examples = arrayOf("Internal storage", "External storage")
        val adapter = CommonListAdapter(examples)
        adapter.setOnItemClickListener(object : CommonListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(
                            baseContext,
                            Internalstorage0Activity::class.java
                        )
                        startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(
                            baseContext,
                            ExternalStorage0MainActivity::class.java
                        )
                        startActivity(intent)
                    }
                }
            }
        })
        viewBinding.rv.adapter = adapter
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(com.example.lib_common.R.drawable.common_list_divider)!!)
        viewBinding.rv.addItemDecoration(itemDecoration)
    }
}