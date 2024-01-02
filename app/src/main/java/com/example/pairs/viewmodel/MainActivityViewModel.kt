package com.example.pairs.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pairs.model.SharedPreferencesPoints

class MainActivityViewModel: ViewModel() {

    private val _points = MutableLiveData<String>()
    val points: LiveData<String> = _points

    fun getPoints(context: Context) {
        _points.value = SharedPreferencesPoints(context).getPoints()
    }

    fun savePoints(context: Context, newPoints: String) {
        SharedPreferencesPoints(context).savePoints(newPoints)
        _points.value = newPoints
    }

    companion object {

        const val SHARED_PREFERENCES = "shared"

    }

}