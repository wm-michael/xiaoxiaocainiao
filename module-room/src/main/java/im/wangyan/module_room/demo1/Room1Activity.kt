package im.wangyan.module_room.demo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import im.wangyan.lib_room_flow.User
import im.wangyan.module_room.databinding.ActivityRoom1Binding

class Room1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRoom1Binding
    lateinit var viewModel: Room1ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoom1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(Room1ViewModel::class.java)

        viewModel.usersLivedata.observe(this) {
            binding.tv.text = "用户列表: ${it.toString()}"
        }

        binding.btnAdd.setOnClickListener {
            // 插入数据
            val text = "幸福是发自内心的满足与平和内心丰盈的人拥有更多在平凡日子里感知幸福的能力趁着时光正好去做你想做的事去享受你热爱的一切当你认真过好每一天生活也会给予你最好的回馈"
            val size = text.length
            val random = (0..size-4).random()
            val nowname = text.subSequence(random, random + 3).toString()
            val user = User(id = null, name = nowname, random)
            viewModel.insertUser(user)
        }

        binding.btnQuery.setOnClickListener {
            binding.tv.text = "loading ... ... "
            viewModel.fetchUsers2()
        }
    }
}