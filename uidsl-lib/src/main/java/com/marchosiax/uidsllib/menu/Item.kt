package com.marchosiax.uidsllib.menu

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class Item(
    val id: Int,
    val title: String,
    val enabled: Boolean,
    val visible: Boolean,
    val checked: Boolean,
    val order: Int,
    @DrawableRes val iconResource: Int,
    val iconDrawable: Drawable?,
    val intent: Intent?,
    val showAsAction:Int,
    val onMenuClick: (() -> Boolean)?
)