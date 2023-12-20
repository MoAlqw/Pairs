package com.example.pairs.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pairs.R
import com.example.pairs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment, MenuView.newInstance("0"))
            .commit()
    }

}