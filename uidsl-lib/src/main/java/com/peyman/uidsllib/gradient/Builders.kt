package com.peyman.uidsllib.gradient

import android.graphics.Color
import androidx.annotation.ColorInt
import com.peyman.uidsllib.GradientDSL

@GradientDSL
class GradientColorBuilder {

    internal companion object {
        val default = GradientColor(
            Color.parseColor("#000000"),
            Color.parseColor("#000000"),
            Color.parseColor("#000000")
        )
    }

    @ColorInt
    var start: Int = Color.parseColor("#000000")
    @ColorInt
    var center: Int = Color.parseColor("#000000")
    @ColorInt
    var end: Int = Color.parseColor("#000000")

    internal fun build() = GradientColor(start, center, end)

}

@GradientDSL
class GradientBuilder {

    private var colors: GradientColor = GradientColorBuilder.default
    var angle: Int = 0
    var useLevel: Boolean = false
    var type: Gradient.Type = Gradient.Type.LINEAR
    var centerX: Float = 0.5f
    var centerY: Float = 0.5f

    fun colors(builder: GradientColorBuilder.() -> Unit) =
        GradientColorBuilder().apply(builder).also { colors = it.build() }

    internal fun build(): Gradient = Gradient(
        colors.startColor,
        colors.centerColor,
        colors.endColor,
        angle,
        useLevel,
        type,
        centerX,
        centerY
    )

}