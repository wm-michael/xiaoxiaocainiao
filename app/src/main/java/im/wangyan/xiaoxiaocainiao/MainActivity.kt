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
import com.example.module_compose.ComposeMainActivity
import com.example.module_content_provider.ContentProviderMainActivity
import com.example.module_exoplayer.ExoPlayerMainActivity
import com.example.module_file_system.FileSystemMainActivity
import com.example.module_glide.GlideMainActivity
import com.example.module_livedata.LivedataMainActivity
import com.example.module_localstore.MainActivityLocalStore
import com.example.module_paging.PagingMainActivity
import com.example.module_service.ServiceMainActivity
import com.example.module_test_compose.demo0.TestCompose0MainActivity
import com.example.module_workmanager.WorkManagerMainActivity
import com.example.module_xlog.XLogMainActivity
import im.wangyan.module_room.RoomMainActivity
import im.wangyan.module_ui_demo.UIDemoActivity


class MainActivity : AppCompatActivity() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val core_list = arrayOf("UI Demo", "Retrofit", "Room")
        val other = arrayOf(
            "Apollo GraphQL", "Glide","DataStore", "XLog", "WorkManager", "ExoPlayer", "Compose","Test-Compose",
            "File System", "Service","ContentProvider","Livedata-dataBinding","Jetpack Paging"
        )
        val dataset = core_list + other
        val customAdapter = CustomAdapter(dataset)
        customAdapter.setOnItemClickListener(object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("XIAOXIAO",  "onItemClick: $position")

                when(position) {

                    0 -> {
                        val intent = Intent(baseContext, UIDemoActivity::class.java)
                        startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(baseContext, im.wangyan.module_retrofit.main.MainActivity::class.java)
                        startActivity(intent)
                    }

                    2 -> {
                        val intent = Intent(baseContext, RoomMainActivity::class.java)
                        startActivity(intent)
                    }

                    3 -> {
                        val intent = Intent(baseContext, ApollographqlActivity::class.java)
                        startActivity(intent)
                    }

                    4 -> {
                        val intent = Intent(baseContext, GlideMainActivity::class.java)
                        startActivity(intent)
                    }

                    5 -> {
                        val intent = Intent(baseContext, MainActivityLocalStore::class.java)
                        startActivity(intent)
                    }

                    6 -> {
                        val intent = Intent(baseContext, XLogMainActivity::class.java)
                        startActivity(intent)
                    }

                    7 -> {
                        val intent = Intent(baseContext, WorkManagerMainActivity::class.java)
                        startActivity(intent)
                    }

                    8 -> {
                        val intent = Intent(baseContext, ExoPlayerMainActivity::class.java)
                        startActivity(intent)
                    }

                    9 -> {
                        val intent = Intent(baseContext, ComposeMainActivity::class.java)
                        startActivity(intent)
                    }

                    10 -> {
                        val intent = Intent(baseContext, TestCompose0MainActivity::class.java)
                        startActivity(intent)
                    }

                    11 -> {
                        val intent = Intent(baseContext, FileSystemMainActivity::class.java)
                        startActivity(intent)
                    }

                    12 -> {
                        val intent = Intent(baseContext, ServiceMainActivity::class.java)
                        startActivity(intent)
                    }

                    13 -> {
                        val intent = Intent(baseContext, ContentProviderMainActivity::class.java)
                        startActivity(intent)
                    }

                    14 -> {
                        val intent = Intent(baseContext, LivedataMainActivity::class.java)
                        startActivity(intent)
                    }

                    15 -> {
                        val intent = Intent(baseContext, PagingMainActivity::class.java)
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