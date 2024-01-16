package com.example.module_localstore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val datastore: DataStore<Preferences>) : ViewModel() {

    val nameFlow: Flow<String> = datastore.data.catch {
        it.printStackTrace()
    }.map { preferences ->
        preferences[PreferenceKeys.NAME] ?: ""
    }

    fun saveNanme(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            datastore.edit { preferences ->
                Log.d("XIAOXIAO", "saveNanme: $name")
                preferences[PreferenceKeys.NAME] = name
            }
        }
    }
}

class MainViewModelFactory(private val datastore: DataStore<Preferences>) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(datastore = datastore) as T
    }
}