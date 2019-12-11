package com.marchosiax.uidsllib.shape

import android.graphics.PorterDuff
import androidx.annotation.ColorInt
import com.marchosiax.uidsllib.gradient.Gradient

class Shape(
    val shapeType: ShapeType,
    val width: Float,
    val height: Float,
    @ColorInt val tint: Int,
    val dither: Boolean,
    val useLevel: Boolean,
    val innerRadius: Float,
    val innerRadiusRatio: Float,
    val inset: Insets,
    val thickness: Float,
    val thicknessRatio: Float,
    val tintMode: PorterDuff.Mode,
    val visible: Boolean,
    val gradient: Gradient?
) {

    enum class ShapeType {
        RECTANGLE, OVAL, RING, LINE
    }

}