package im.wangyan.module_room

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib_common.adapter.CommonListAdapter
import im.wangyan.module_room.databinding.ActivityRoomMainBinding
import im.wangyan.module_room.demo0.Room0Activity
import im.wangyan.module_room.demo1.Room1Activity

class RoomMainActivity : AppCompatActivity() {
    private lateinit var viewbinding: ActivityRoomMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityRoomMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        val room_list = arrayOf("Room-suspend", "Room-flow")
        val adapter = CommonListAdapter(room_list)
        adapter.setOnItemClickListener(object : CommonListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(baseContext, Room0Activity::class.java)
                        startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(baseContext, Room1Activity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })
        val layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        viewbinding.rv.layoutManager = layoutManager
        viewbinding.rv.adapter = adapter

        val  dividerItemDecoration = DividerItemDecoration(baseContext, layoutManager.orientation)
        dividerItemDecoration.setDrawable(getDrawable(com.example.lib_common.R.drawable.common_list_divider)!!)
        viewbinding.rv.addItemDecoration(dividerItemDecoration)
    }
}