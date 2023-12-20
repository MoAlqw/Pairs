package com.example.pairs.model

import android.content.Context
import com.example.pairs.viewmodel.MainActivityViewModel
import com.example.pairs.viewmodel.MainActivityViewModel.Companion.POINTS_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPreferencesPoints(val context: Context) {

    suspend fun getPoints(): String {
        return withContext(Dispatchers.IO) {
            val sharedPreferences = context.getSharedPreferences(
                MainActivityViewModel.SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )
            return@withContext sharedPreferences.getString(POINTS_KEY, "0").toString()
        }
    }

}