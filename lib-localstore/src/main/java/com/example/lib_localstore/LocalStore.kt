package com.example.lib_localstore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow


private const val PREFERENCES_NAME = "preferences_datastore_xiaoxiaocainiao"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class LocalStoreManger private constructor() {

    private var isInit = false
    private lateinit var context: Context

    companion object {
        val instance: LocalStoreManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LocalStoreManger()
        }
    }

    fun init(context: Context) {
        if (isInit) return
        this.context = context
        isInit = true
    }

    fun getData(): Flow<Preferences> {
        return context.dataStore.data
    }

    suspend fun saveNanme(name: String) {
        context.dataStore.edit { preferences ->
            Log.d("XIAOXIAO", "LocalStoreManger saveNanme: $name")
            preferences[PreferenceKeys.NAME] = name
        }
    }
}