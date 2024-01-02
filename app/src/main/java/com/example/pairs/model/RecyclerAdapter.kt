package com.example.pairs.model

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pairs.Diamond
import com.example.pairs.R
import com.example.pairs.view.GameScene
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerAdapter(
    private val dataList: List<Diamond>
): RecyclerView.Adapter<RecyclerAdapter.DiamondViewHolder>() {

    private val lst = mutableListOf<DiamondForAdapter>()

    class DiamondViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.card_item)
        val imgDiamond: AppCompatImageButton = view.findViewById(R.id.img_diamond)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiamondViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return DiamondViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DiamondViewHolder, position: Int) {

        val flipStart = ObjectAnimator.ofFloat(holder.card, "scaleX", 1.0f, -1.0f).apply{
            duration = 300
            interpolator = LinearInterpolator()
        }
        val flipEnd = ObjectAnimator.ofFloat(holder.card, "scaleX", -1.0f, 1.0f).apply{
            duration = 300
            interpolator = LinearInterpolator()
        }
        holder.imgDiamond.setImageResource(dataList[position].img)
        holder.card.setOnClickListener {
            if (lst.size != 2) {
                CoroutineScope(Job()).launch {
                    withContext(Dispatchers.Main) {
                        flipStart.start()
                        delay(150)
                        holder.imgDiamond.visibility = View.VISIBLE
                        holder.imgDiamond.isEnabled = false
                    }
                    lst.add(DiamondForAdapter(holder.imgDiamond, position, flipEnd))
                    withContext(Dispatchers.Main) { check() }
                }
            }
        }
    }

    private suspend fun check() {
        if (lst.size % 2 == 0 && lst.size != 0) {
            if (dataList[lst[lst.lastIndex].position].img != dataList[lst[lst.lastIndex-1].position].img) {
                delay(250)
                lst.forEach {
                    withContext(Dispatchers.Main) {
                        it.anim.start()
                        delay(150)
                        it.img.visibility = View.INVISIBLE
                        it.img.isEnabled = true
                        notifyItemChanged(it.position)
                    }
                }
            } else {
                GameScene.counterOfPairs.value = GameScene.counterOfPairs.value!! + 1
            }
            lst.clear()
        }
    }

}