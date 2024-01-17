package im.wangyan.xiaoxiaocainiao

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module_apollographql.ApollographqlActivity
import com.example.module_glide.GlideMainActivity
import com.example.module_localstore.MainActivityLocalStore
import com.example.module_workmanager.WorkManagerMainActivity
import com.example.module_xlog.XLogMainActivity
import im.wangyan.module_room.demo0.Room0Activity
import im.wangyan.module_room.demo1.Room1Activity
import im.wangyan.module_ui_demo.UIDemoActivity


class MainActivity : AppCompatActivity() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ui_demos_list = arrayOf("UI demo")
        val retrofit_list = arrayOf("Retrofit")
        val room_list = arrayOf("Room-suspend", "Room-flow")
        val other = arrayOf("Apollo GraphQL", "Glide","DataStore", "Xlog", "WorkManager")
        val dataset = retrofit_list + room_list + ui_demos_list + other
        val customAdapter = CustomAdapter(dataset)
        customAdapter.setOnItemClickListener(object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("XIAOXIAO",  "onItemClick: $position")

                when(position) {

                    0 -> {
                        val intent = Intent(baseContext, im.wangyan.module_retrofit.main.MainActivity::class.java)
                        startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(baseContext, Room0Activity::class.java)
                        startActivity(intent)
                    }

                    2 -> {
                        val intent = Intent(baseContext, Room1Activity::class.java)
                        startActivity(intent)
                    }

                    3 -> {
                        val intent = Intent(baseContext, UIDemoActivity::class.java)
                        startActivity(intent)
                    }

                    4 -> {
                        val intent = Intent(baseContext, ApollographqlActivity::class.java)
                        startActivity(intent)
                    }

                    5 -> {
                        val intent = Intent(baseContext, GlideMainActivity::class.java)
                        startActivity(intent)
                    }

                    6 -> {
                        val intent = Intent(baseContext, MainActivityLocalStore::class.java)
                        startActivity(intent)
                    }

                    7 -> {
                        val intent = Intent(baseContext, XLogMainActivity::class.java)
                        startActivity(intent)
                    }

                    8 -> {
                        val intent = Intent(baseContext, WorkManagerMainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })

        val rv = findViewById<RecyclerView>(R.id.rv)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = layoutManager
        rv.adapter = customAdapter

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)
        rv.addItemDecoration(itemDecoration)
    }
}