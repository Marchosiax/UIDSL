package com.marchosiax.uidsllib.shape

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.os.Build
import androidx.annotation.ColorInt
import com.marchosiax.uidsllib.ShapeDSL
import com.marchosiax.uidsllib.gradient.Gradient
import com.marchosiax.uidsllib.gradient.GradientBuilder

@ShapeDSL
class SolidBuilder {

    @ColorInt
    var color: Int = Color.WHITE

    internal fun build() = Solid(color)

}

@ShapeDSL
class CornerBuilder {

    var topLeft: Float = 0f
    var topRight: Float = 0f
    var bottomRight: Float = 0f
    var bottomLeft: Float = 0f
    var radius: Float = -414f

    val noRadius = Corner(0f, 0f, 0f, 0f)

    internal fun build() = if (radius != -414f)
        Corner(radius, radius, radius, radius)
    else
        Corner(topLeft, topRight, bottomRight, bottomLeft)

}

@ShapeDSL
class StrokeBuilder {

    @ColorInt
    var color: Int = Color.BLACK
    var width: Int = 0
    var dashGap: Float = 0f
    var dashWidth: Float = 0f

    internal fun build() = Stroke(color, width, dashGap, dashWidth)

}

@ShapeDSL
class PaddingBuilder {

    var bottom: Int = 0
    var left: Int = 0
    var top: Int = 0
    var right: Int = 0
    var all: Int = -1001

    internal fun build() =
        if (all != -1001) Padding(
            all,
            all,
            all,
            all
        ) else Padding(bottom, left, top, right)

}

@ShapeDSL
class InsetBuilder {

    var bottom: Int = 0
    var left: Int = 0
    var top: Int = 0
    var right: Int = 0

    internal fun build() = Insets(bottom, left, top, right)

}

@ShapeDSL
class ThicknessBuilder {

    var thickness: Int = 0
    var thicknessRatio: Float = 0f

    fun build() = Thickness(thickness, thicknessRatio)
}

@ShapeDSL
class SizeBuilder {

    var width: Int = -1
    var height: Int = -1

    fun build() = Size(width, height)

}

@ShapeDSL
class ShapeBuilder {

    @ColorInt
    private var tint: Int = Color.TRANSPARENT
    @ColorInt
    private var color: Int = Color.WHITE
    private var dither: Boolean = false
    private var tintMode: PorterDuff.Mode = PorterDuff.Mode.ADD
    private var visible: Boolean = true
    private var restart: Boolean = false
    private var padding: Padding = Padding(0, 0, 0, 0)
    private var corner: Corner = Corner(0f, 0f, 0f, 0f)
    private var size: Size? = null
    private var thickness: Thickness? = null
    private var gradient: Gradient? = null
    private var stroke: Stroke? = null
    private var inset: Insets? = null

    fun solid(builder: SolidBuilder.() -> Unit) =
        SolidBuilder().apply(builder).also { color = it.color }

    fun size(builder: SizeBuilder.() -> Unit) =
        SizeBuilder().apply(builder).also { size = it.build() }

    fun corner(builder: CornerBuilder.() -> Unit) =
        CornerBuilder().apply(builder).also { corner = it.build() }

    fun stroke(builder: StrokeBuilder.() -> Unit) =
        StrokeBuilder().apply(builder).also { stroke = it.build() }

//    fun padding(builder: PaddingBuilder.() -> Unit) =
//        PaddingBuilder().apply(builder).also { padding = it.build() }

    fun inset(builder: InsetBuilder.() -> Unit) =
        InsetBuilder().apply(builder).also { inset = it.build() }

    fun gradient(builder: GradientBuilder.() -> Unit) =
        GradientBuilder().apply(builder).also { gradient = it.build() }

    fun thickness(builder: ThicknessBuilder.() -> Unit) =
        ThicknessBuilder().apply(builder).also { thickness = it.build() }

    internal fun build(type: Shape.ShapeType) = GradientDrawable().apply {
        val shapeType = when (type) {
            Shape.ShapeType.LINE -> GradientDrawable.LINE
            Shape.ShapeType.RING -> GradientDrawable.RING
            Shape.ShapeType.OVAL -> GradientDrawable.OVAL
            Shape.ShapeType.RECTANGLE -> GradientDrawable.RECTANGLE
        }

        shape = shapeType
        cornerRadii = floatArrayOf(
            corner.topLeft,
            corner.topLeft,
            corner.topRight,
            corner.topRight,
            corner.bottomRight,
            corner.bottomRight,
            corner.bottomLeft,
            corner.bottomLeft
        )

        setDither(dither)
        setVisible(visible, restart)
        setColor(this@ShapeBuilder.color)
        //setPadding(padding.left, padding.top, padding.right, padding.bottom)

        size?.let { setSize(it.width, it.height) }

        gradient?.let {
            colors = intArrayOf(it.startColor, it.centerColor, it.endColor)
            orientation = it.getOrientation()
            useLevel = it.useLevel
            gradientType = shapeType
            setGradientCenter(it.centerX, it.centerY)
        }

        stroke?.let {
            setStroke(it.width, it.color, it.dashWidth, it.dashGap)
        }

        this@ShapeBuilder.thickness?.let {
            thickness = it.thickness
            thicknessRatio = it.thicknessRatio
        }

        inset?.let {

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTint(tint)
            setTintMode(tintMode)
        }
    }

}