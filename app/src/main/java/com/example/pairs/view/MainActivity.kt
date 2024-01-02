package com.example.pairs.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pairs.R
import com.example.pairs.databinding.ActivityMainBinding
import com.example.pairs.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModelActivity by lazy { MainActivityViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        viewModelActivity.getPoints(this)
        launchFragment(MenuView.newInstance(viewModelActivity.points.value ?: "-1"))
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }
}