package com.pss.djmw_android.widget.utils


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pss.djmw_android.widget.utils.DataStoreKey.DEFAULT_USER_INFO_SAVE
import com.pss.djmw_android.widget.utils.DataStoreKey.PREFERENCE_NAME
import com.pss.djmw_android.widget.utils.DataStoreKey.PREFERENCE_USER_INFO_SAVE
import com.pss.djmw_android.widget.utils.DataStoreModule.PreferencesKeys.dataStoreUserInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(PREFERENCE_NAME)

class DataStoreModule @Inject constructor(@ApplicationContext private val context: Context){

    private object PreferencesKeys {
        val dataStoreUserInfo = stringPreferencesKey(PREFERENCE_USER_INFO_SAVE)
    }

    private val dataStore: DataStore<Preferences> =
        context.dataStore


    //쓰기
    suspend fun setUserInfoSave(text: String) {
        context.dataStore.edit { preferences ->
            preferences[dataStoreUserInfo] = text
        }
    }

    // 읽기
    val readUserInfoSave: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[dataStoreUserInfo] ?: DEFAULT_USER_INFO_SAVE
        }
}