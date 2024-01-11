package im.wangyan.module_room

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import im.wangyan.lib_room.Todo
import im.wangyan.lib_room.TodoDatabase
import im.wangyan.module_room.databinding.ActivityRoom0Binding

class Room0Activity : AppCompatActivity(), TodoAdapter.TodoClickListener {

    private lateinit var binding: ActivityRoom0Binding
    private lateinit var database: TodoDatabase
    lateinit var viewModel: TodoViewModel
    lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoom0Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        viewModel.allTodo.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }
        }

        database = TodoDatabase.getDatabase(this)
    }

    private fun initUI() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = TodoAdapter(this, this)
        binding.recyclerView.adapter = adapter

        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val todo = result.data?.getSerializableExtra("todo") as? Todo
                    if (todo != null) {
                        viewModel.insertTodo(todo)
                    }
                }
            }

        binding.fabAddTodo.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            getContent.launch(intent)
        }

    }

    private val updateOrDeleteTodo =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val todo = result.data?.getSerializableExtra("todo") as Todo
                val isDelete = result.data?.getBooleanExtra("delete_todo", false) as Boolean
                if (todo != null && !isDelete) {
                    viewModel.updateTodo(todo)
                } else if (todo != null && isDelete) {
                    viewModel.deleteTodo(todo)
                }
            }
        }


    override fun onItemClicked(todo: Todo) {
        val intent = Intent(this@Room0Activity, AddTodoActivity::class.java)
        intent.putExtra("current_todo", todo)
        updateOrDeleteTodo.launch(intent)
    }
}