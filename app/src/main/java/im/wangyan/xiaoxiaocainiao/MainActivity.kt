package im.wangyan.xiaoxiaocainiao

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataset = arrayOf("retrofit-call", "retrofit-suspend", "retrofit-flow")
        val customAdapter = CustomAdapter(dataset)
        customAdapter.setOnItemClickListener(object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("XIAOXIAO",  "onItemClick: $position")

                when(position) {
                    0 -> {
                        val intent = Intent(baseContext, im.wangyan.module_retrofit.Retrofit1Activity::class.java)
                        startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(baseContext, im.wangyan.module_retrofit.Retrofit0Activity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })

        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = customAdapter

    }
}