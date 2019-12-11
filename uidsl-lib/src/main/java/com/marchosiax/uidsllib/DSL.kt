package com.marchosiax.uidsllib

import android.view.Menu
import com.marchosiax.uidsllib.gradient.GradientBuilder
import com.marchosiax.uidsllib.menu.MenuBuilder
import com.marchosiax.uidsllib.shape.Shape
import com.marchosiax.uidsllib.shape.ShapeBuilder
import com.marchosiax.uidsllib.state.SelectorBuilder

fun gradient(body: GradientBuilder.() -> Unit) = GradientBuilder().apply(body).build()

fun rectangle(builder: ShapeBuilder.() -> Unit) =
    ShapeBuilder().apply(builder).build(Shape.ShapeType.RECTANGLE)

fun oval(builder: ShapeBuilder.() -> Unit) =
    ShapeBuilder().apply(builder).build(Shape.ShapeType.OVAL)

fun ring(builder: ShapeBuilder.() -> Unit) =
    ShapeBuilder().apply(builder).build(Shape.ShapeType.RING)

fun line(builder: ShapeBuilder.() -> Unit) =
    ShapeBuilder().apply(builder).build(Shape.ShapeType.LINE)

fun selector(builder: SelectorBuilder.() -> Unit) = SelectorBuilder().apply(builder).build()

fun Menu.build(builder: MenuBuilder.() -> Unit) {
    val m = MenuBuilder().apply(builder).build()
    m.items.forEach { add(Menu.NONE, it.id, it.order, it.title) }
    m.groups.forEach { group -> group.items.forEach { add(group.id, it.id, it.order, it.title) } }

}