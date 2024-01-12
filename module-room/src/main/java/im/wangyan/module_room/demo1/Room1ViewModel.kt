package im.wangyan.module_room.demo1

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import im.wangyan.lib_room_flow.AppDatabase
import im.wangyan.lib_room_flow.DatabaseBuilder
import im.wangyan.lib_room_flow.DatabaseHelper
import im.wangyan.lib_room_flow.DatabaseHelperImpl
import im.wangyan.lib_room_flow.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class Room1ViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper: DatabaseHelper

    private val appDatabase: AppDatabase

    init {
        appDatabase = DatabaseBuilder.getInstance(application)
        dbHelper = DatabaseHelperImpl(appDatabase)
        fetchUsers()
    }

    var usersLivedata: MutableLiveData<List<User>> = MutableLiveData(listOf())

    fun fetchUsers() {
        viewModelScope.launch {
            dbHelper.getUsers()
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.d("XIAOXIAO", "loading...")
                }
                .catch { e ->
                    // handle exception
                    Log.d("XIAOXIAO", e.toString())
                }
                .collect {
                    // list of users from the database
                    Log.d("XIAOXIAO", "fetchUsers complete")
                    usersLivedata.value = it
                }
        }
    }

    //完整流程，不借助中间层
    fun fetchUsers2() {
        viewModelScope.launch {
            flow {
                val users = appDatabase.userDao().getAll()
                emit(users)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    Log.d("XIAOXIAO", "loading...type 2")
                    delay(1500)
                }.catch { e ->
                    // handle exception
                    Log.d("XIAOXIAO", e.toString())
                }
                .collect {
                    // list of users from the database
                    Log.d("XIAOXIAO", "fetchUsers complete. type 2")
                    usersLivedata.value = it
                }
        }

    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            dbHelper.insertAll(listOf(user)).flowOn(Dispatchers.IO)
                .onStart { Log.d("XIAOXIAO", "inserting...") }
                .catch { e -> Log.d("XIAOXIAO", e.toString()) }.collect {
                    Log.d("XIAOXIAO", "insertUser complete")
                    fetchUsers2()
                }
        }
    }

}