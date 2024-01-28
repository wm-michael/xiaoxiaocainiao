package com.example.module_paging

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib_common.adapter.CommonListAdapter
import com.example.module_paging.databinding.ActivityPagingMainBinding
import com.example.module_paging.demo0.PagingDemo0Activity

class PagingMainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityPagingMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityPagingMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewBinding.rv.layoutManager = layoutManager

        val examples = arrayOf("Paging 3", "To be continued")
        val adapter = CommonListAdapter(examples)
        adapter.setOnItemClickListener(object : CommonListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(
                            baseContext,
                            PagingDemo0Activity::class.java
                        )
                        startActivity(intent)
                    }

                    1 -> {

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