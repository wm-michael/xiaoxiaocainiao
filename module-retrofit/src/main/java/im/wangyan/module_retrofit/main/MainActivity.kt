package im.wangyan.module_retrofit.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import im.wangyan.module_retrofit.R
import im.wangyan.module_retrofit.Retrofit0Activity
import im.wangyan.module_retrofit.Retrofit1Activity
import im.wangyan.module_retrofit.Retrofit2.Retrofit2Activity
import im.wangyan.module_retrofit.Retrofit3.Retrofit3Activity
import im.wangyan.module_retrofit.Retrofit4.Retrofit4Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit_list = arrayOf("retrofit-call", "retrofit-suspend", "retrofit-flow", "retrofit-flow-result", "retrofit-gson-convert")
        val adapter = CustomRecycleViewAdapter(retrofit_list)

        val rv = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv)
        rv.adapter = adapter

        val layoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = layoutManager

        val itemDecoration = GridSpaceItemDecoration(2, 10,10)
        rv.addItemDecoration(itemDecoration)

        adapter.setOnItemClickListener(object : CustomRecycleViewAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("XIAOXIAO",  "onItemClick: $position")

                when(position) {

                    0 -> {
                        val intent = Intent(baseContext, Retrofit0Activity::class.java)
                        startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(baseContext, Retrofit1Activity::class.java)
                        startActivity(intent)
                    }

                    2 -> {
                        val intent = Intent(baseContext, Retrofit2Activity::class.java)
                        startActivity(intent)
                    }

                    3 -> {
                        val intent = Intent(baseContext, Retrofit3Activity::class.java)
                        startActivity(intent)
                    }

                    4 -> {
                        val intent = Intent(baseContext, Retrofit4Activity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}