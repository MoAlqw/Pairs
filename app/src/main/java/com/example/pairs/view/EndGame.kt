package com.example.pairs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pairs.R
import com.example.pairs.databinding.FragmentEndGameBinding

class EndGame : Fragment() {

    private lateinit var binding: FragmentEndGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEndGameBinding.inflate(inflater, container, false)

        binding.btnHome.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, MenuView())
                .commit()
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(points: String) =
            EndGame().apply {
                arguments = Bundle().apply {
                }
            }
    }
}