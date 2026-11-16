package com.bigbigdreams.dreamax.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dreamax_settings")

class AppPreferences(private val context: Context) {

    val wifiOnlyDownloads: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_WIFI_ONLY] ?: false
    }

    val autoSyncLibrary: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_AUTO_SYNC] ?: true
    }

    val lastSyncAt: Flow<Long> = context.dataStore.data.map { prefs ->
        prefs[KEY_LAST_SYNC]?.toLongOrNull() ?: 0L
    }

    suspend fun setWifiOnlyDownloads(enabled: Boolean) {
        context.dataStore.edit { it[KEY_WIFI_ONLY] = enabled }
    }

    suspend fun setAutoSyncLibrary(enabled: Boolean) {
        context.dataStore.edit { it[KEY_AUTO_SYNC] = enabled }
    }

    suspend fun setLastSyncAt(epochMs: Long) {
        context.dataStore.edit { it[KEY_LAST_SYNC] = epochMs.toString() }
    }

    companion object {
        private val KEY_WIFI_ONLY = booleanPreferencesKey("wifi_only_downloads")
        private val KEY_AUTO_SYNC = booleanPreferencesKey("auto_sync_library")
        private val KEY_LAST_SYNC = stringPreferencesKey("last_sync_at")
    }
}
