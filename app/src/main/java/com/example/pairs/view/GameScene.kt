package com.example.pairs.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairs.Diamond
import com.example.pairs.R
import com.example.pairs.model.RecyclerAdapter
import com.example.pairs.databinding.FragmentGameSceneBinding

class GameScene : Fragment() {

    private lateinit var points: String
    private lateinit var binding: FragmentGameSceneBinding

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

        binding = FragmentGameSceneBinding.inflate(inflater, container, false)

        val totalTimeInMillis: Long = 39000
        val intervalInMillis: Long = 1000

        val countDownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                if (timeLeft > 9)
                    binding.tvTimerSec.text = "0:$timeLeft"
                else
                    binding.tvTimerSec.text = "0:0$timeLeft"
            }

            override fun onFinish() {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment, EndGame())
                    .commit()
            }
        }

        val rcView: RecyclerView = binding.rvGameTable
        rcView.layoutManager = GridLayoutManager(context, 4)
        val adapter = RecyclerAdapter(Diamond.loadDiamonds().shuffled())
        rcView.adapter = adapter
        countDownTimer.start()
        return binding.root
    }

    companion object {

        private const val KEY_POINTS = "points"

        @JvmStatic
        fun newInstance(points: String) =
            GameScene().apply {
                arguments = Bundle().apply {
                    putString(KEY_POINTS, points)
                }
            }
    }
}