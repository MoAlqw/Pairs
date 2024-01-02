package com.example.pairs.model

import android.content.Context
import com.example.pairs.viewmodel.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPreferencesPoints(val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        MainActivityViewModel.SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )

    fun getPoints(): String {
        return sharedPreferences.getString(POINTS_KEY, "0")!!
    }

     fun savePoints(points: String) {
         sharedPreferences.edit().putString(POINTS_KEY, points).apply()
    }

    companion object {

        const val POINTS_KEY = "points"

    }
}