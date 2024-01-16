package com.example.lib_localstore

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore

object LocalStore {
    const val SOTRE_NAME = "xiaoxiao_data_store"


    val Context.dataStrore by preferencesDataStore(SOTRE_NAME, produceMigrations = {
                context ->
        listOf(SharedPreferencesMigration(context, SOTRE_NAME))
    })

    //TODO：需要把datastore封装一下，根据key的Type和Value获取数据、设置数据

}