package com.example.pairs.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pairs.model.SharedPreferencesPoints
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

//    fun get_points(context: Context): String {
//
//        val points = async {
//            SharedPreferencesPoints(context).getPoints()
//        }
//    }

    companion object{
        const val SHARED_PREFERENCES = "shared"
        const val POINTS_KEY = "points"
    }

}