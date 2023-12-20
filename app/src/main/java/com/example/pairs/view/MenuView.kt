package com.example.pairs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pairs.R
import com.example.pairs.databinding.FragmentMenuViewBinding

class MenuView : Fragment() {

    private lateinit var points: String
    private lateinit var binding: FragmentMenuViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            points = it.getString(KEY_POINTS)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuViewBinding.inflate(inflater, container, false)

        binding.license.setOnClickListener{
            showToast()
        }

        binding.btnStartGame.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, GameScene())
                .commit()
        }

        return binding.root

    }

    private fun showToast() {
        Toast.makeText(context, "Created by Nikita Guzenko", Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val KEY_POINTS = "points"

        @JvmStatic
        fun newInstance(points: String) =
            MenuView().apply {
                arguments = Bundle().apply {
                    putString(KEY_POINTS, points)
                }
            }
    }
}