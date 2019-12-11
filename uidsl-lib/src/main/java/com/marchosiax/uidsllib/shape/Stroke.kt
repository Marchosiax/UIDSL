package com.marchosiax.uidsllib.shape

import androidx.annotation.ColorInt

data class Stroke(
    @ColorInt val color: Int,
    val width: Int,
    val dashGap: Float,
    val dashWidth: Float
)