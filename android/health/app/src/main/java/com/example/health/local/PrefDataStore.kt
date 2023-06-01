package com.example.health.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class PrefDataStore(private val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

    private val accessTokenKey = stringPreferencesKey("AccessTokenKey")

    suspend fun setAccessToken(accessToken: String) {
        context.dataStore.edit { it[accessTokenKey] = accessToken }
    }

    suspend fun getAccessToken(): String? {
        return context.dataStore.data.map { it[accessTokenKey] }.firstOrNull()
    }
}