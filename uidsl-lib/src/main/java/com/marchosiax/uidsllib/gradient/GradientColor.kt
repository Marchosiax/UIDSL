package com.marchosiax.uidsllib.gradient

import androidx.annotation.ColorInt

data class GradientColor(
    @ColorInt
    var startColor: Int,
    @ColorInt
    var centerColor: Int,
    @ColorInt
    var endColor: Int
)