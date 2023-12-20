package com.example.pairs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairs.Diamond
import com.example.pairs.model.RecyclerAdapter
import com.example.pairs.databinding.FragmentGameSceneBinding

//    val countDownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
    //    override fun onTick(millisUntilFinished: Long) {
    //        // Выполняется каждый раз, когда таймер тикает (каждую секунду в данном случае)
    //        val timeLeft = millisUntilFinished / 1000 // Переводим миллисекунды в секунды
    //        // TODO: Обновление интерфейса с отображением времени (например, textView.text = timeLeft.toString())
    //    }
    //
    //    override fun onFinish() {
    //        // Выполняется по завершению таймера (по истечению totalTimeInMillis)
    //        // TODO: Логика, которую нужно выполнить после завершения таймера
    //    }
//   }

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

        val rcView: RecyclerView = binding.rvGameTable
        rcView.layoutManager = GridLayoutManager(context, 4)
        val adapter = RecyclerAdapter(Diamond.loadDiamonds().shuffled())
        rcView.adapter = adapter

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