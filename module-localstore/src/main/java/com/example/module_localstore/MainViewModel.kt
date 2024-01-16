package com.example.module_localstore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lib_localstore.LocalStoreManger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val logTag: String) : ViewModel() {

    init {
        Log.d("XIAOXIAO", "参数$logTag 没有任何作用，只是秀一下自定义ViewModel传递参数")
    }

    val nameFlow: Flow<String> = LocalStoreManger.instance.getData().catch {
        it.printStackTrace()
    }.map { preferences ->
        preferences[PreferenceKeys.NAME] ?: ""
    }

    fun saveNanme(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            LocalStoreManger.instance.saveNanme(name)
        }
    }
}

class MainViewModelFactory(private val logTag: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(logTag = logTag) as T
    }
}