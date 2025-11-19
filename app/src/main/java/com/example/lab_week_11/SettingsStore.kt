package com.example.lab_week_11

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val SETTINGS_STORE_NAME = "settingsStore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS_STORE_NAME)

class SettingsStore(private val context: Context) {

    private companion object {
        val KEY_TEXT = stringPreferencesKey("key_text")
    }

    val textFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_TEXT] ?: ""
        }

    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }
}
