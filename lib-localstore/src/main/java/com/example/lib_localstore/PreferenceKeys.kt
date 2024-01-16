package com.example.lib_localstore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val NAME = stringPreferencesKey("name")
    val ID = stringPreferencesKey("id")
}