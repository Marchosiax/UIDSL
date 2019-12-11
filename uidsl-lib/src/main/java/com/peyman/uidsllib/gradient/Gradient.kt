package com.peyman.uidsllib.gradient

import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt
import com.peyman.uidsllib.ShapeDSL

@ShapeDSL
data class Gradient(
    @ColorInt val startColor: Int,
    @ColorInt val centerColor: Int,
    @ColorInt val endColor: Int,
    val angle: Int,
    val useLevel: Boolean,
    val type: Type,
    val centerX: Float,
    val centerY: Float
) {

    internal fun getOrientation() = when (angle % 360) {
        0 -> GradientDrawable.Orientation.LEFT_RIGHT
        45 -> GradientDrawable.Orientation.BL_TR
        90 -> GradientDrawable.Orientation.BOTTOM_TOP
        135 -> GradientDrawable.Orientation.BR_TL
        180 -> GradientDrawable.Orientation.RIGHT_LEFT
        225 -> GradientDrawable.Orientation.TR_BL
        270 -> GradientDrawable.Orientation.TOP_BOTTOM
        315 -> GradientDrawable.Orientation.TL_BR
        else -> GradientDrawable.Orientation.LEFT_RIGHT
    }

    internal fun getType() = when (type) {
        Type.LINEAR -> GradientDrawable.LINEAR_GRADIENT
        Type.RADIAL -> GradientDrawable.RADIAL_GRADIENT
        Type.SWEEP -> GradientDrawable.SWEEP_GRADIENT
    }

    enum class Type {
        LINEAR, RADIAL, SWEEP
    }

}