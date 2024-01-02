package com.example.pairs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pairs.R
import com.example.pairs.databinding.FragmentEndGameBinding
import com.example.pairs.model.SharedPreferencesPoints
import com.example.pairs.viewmodel.MainActivityViewModel

class EndGame : Fragment() {

    private lateinit var binding: FragmentEndGameBinding
    private lateinit var points: String
    private lateinit var reward: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            points = it.getString(KEY_POINTS)!!
            reward = it.getString(KEY_REWARD)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEndGameBinding.inflate(inflater, container, false)

        binding.tvCoins.text = reward

        binding.btnDoubleReward.setOnClickListener {
            reward = (reward.toInt() * 2).toString()
            binding.tvCoins.text = reward
        }

        binding.btnHome.setOnClickListener {

            val resPoints = (reward.toInt() + points.toInt()).toString()

            MainActivityViewModel()
                .savePoints(requireContext(), resPoints)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, MenuView.newInstance(resPoints))
                .commit()
        }

        return binding.root
    }

    companion object {

        private const val KEY_REWARD = "reward"
        private const val KEY_POINTS = "points"

        @JvmStatic
        fun newInstance(points: String, reward: String) =
            EndGame().apply {
                arguments = Bundle().apply {
                    putString(KEY_POINTS, points)
                    putString(KEY_REWARD, reward)
                }
            }
    }
}