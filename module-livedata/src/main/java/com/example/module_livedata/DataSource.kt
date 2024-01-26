package com.example.module_livedata

import androidx.lifecycle.LiveData

interface DataSource {
    fun getCurrentTime(): LiveData<Long>
    fun fetchWeather(): LiveData<String>
    val cachedData: LiveData<String>
    suspend fun fetchNewData()
}