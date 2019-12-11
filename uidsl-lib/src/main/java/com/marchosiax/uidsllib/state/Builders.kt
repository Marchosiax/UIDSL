package com.marchosiax.uidsllib.state

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.AttrRes
import com.marchosiax.uidsllib.SelectorDSL

@SelectorDSL
class StateBuilder {

    var drawable: Drawable? = null

    fun build(state: Int = android.R.attr.state_pressed): State {
        checkNotNull(drawable) { "Drawable cannot be null" }
        return State(state, drawable!!)
    }

}

@SelectorDSL
class MultipleStateBuilder {

    var drawable: Drawable? = null
    val states = ArrayList<Int>()

    fun build(): MultipleStates {
        checkNotNull(drawable) { "Drawable cannot be null" }
        return MultipleStates(states, drawable!!)
    }

}

class SelectorBuilder {

    private val states = ArrayList<State>()
    var exitFadeDuration: Int = 400
    var enterFadeDuration: Int = 400

    private fun addState(state: State) {
        states.add(state)
    }


    fun enabled(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(android.R.attr.state_enabled))
    }

    fun disabled(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(-android.R.attr.state_enabled))
    }

    fun pressed(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(android.R.attr.state_pressed))
    }

    fun notPressed(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(-android.R.attr.state_pressed))
    }

    fun selected(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(android.R.attr.state_selected))
    }

    fun notSelected(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(-android.R.attr.state_selected))
    }

    fun checked(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(android.R.attr.state_checked))
    }

    fun unchecked(builder: StateBuilder.() -> Unit) = StateBuilder().apply(builder).also {
        addState(it.build(-android.R.attr.state_checked))
    }

    fun stateOf(@AttrRes state: Int, builder: StateBuilder.() -> Unit) =
        StateBuilder().apply(builder).also { addState(it.build(state)) }

    fun build() = StateListDrawable().apply {
        setExitFadeDuration(exitFadeDuration)
        setEnterFadeDuration(enterFadeDuration)
        states.forEach { addState(intArrayOf(it.state), it.shape) }
    }

}