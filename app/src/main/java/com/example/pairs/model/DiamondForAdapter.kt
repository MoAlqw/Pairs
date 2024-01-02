package com.example.pairs.model

import android.animation.ObjectAnimator
import androidx.appcompat.widget.AppCompatImageButton

class DiamondForAdapter(
    val img: AppCompatImageButton,
    val position: Int,
    val anim: ObjectAnimator
)