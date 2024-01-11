package im.wangyan.module_room

import androidx.lifecycle.LiveData
import im.wangyan.lib_room.Todo
import im.wangyan.lib_room.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }

    suspend fun update(todo: Todo){
        todoDao.update(todo.id, todo.title, todo.note)
    }
}