package com.example.pairs.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
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
        points = arguments?.getString(KEY_POINTS) ?: "0"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGameSceneBinding.inflate(inflater, container, false)

        binding.tvCoins.text = points

        val totalTimeInMillis: Long = 39000
        val intervalInMillis: Long = 1000
        var timeHas: Long = 39

        val countDownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeHas = timeLeft
                if (timeLeft > 9)
                    binding.tvTimerSec.text = "0:$timeLeft"
                else
                    binding.tvTimerSec.text = "0:0$timeLeft"
            }

            override fun onFinish() {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment, EndGame.newInstance(points, "10"))
                    .commit()
            }
        }

        counterOfPairs.observe(viewLifecycleOwner) { data ->
            if (data == 10) {
                countDownTimer.cancel()
                val reward = if (timeHas >= 20) 100 else (100 - (20 - timeHas) * 5)
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment,
                        EndGame.newInstance(points, if (reward < 10) "10" else reward.toString())
                    )
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
        val counterOfPairs = MutableLiveData(0)

        @JvmStatic
        fun newInstance(points: String) =
            GameScene().apply {
                arguments = Bundle().apply {
                    putString(KEY_POINTS, points)
                }
            }
    }
}