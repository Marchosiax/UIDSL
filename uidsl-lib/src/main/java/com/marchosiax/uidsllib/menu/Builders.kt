package com.marchosiax.uidsllib.menu

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.marchosiax.uidsllib.MenuDSL

@MenuDSL
@Suppress("MemberVisibilityCanBePrivate")
class ItemBuilder {

    var id: Int = 0
    var title: String = ""
    var isEnabled: Boolean = true
    var isVisible: Boolean = true
    var isChecked: Boolean = false
    var order = 0
    @DrawableRes
    var iconResource: Int = 0
    var iconDrawable: Drawable? = null
    var intent: Intent? = null
    private var onMenuClick: (() -> Boolean)? = null

    fun onClick(onClick: () -> Boolean) {
        this.onMenuClick = onClick
    }

    internal fun build(): Item {
        return Item(
            id, title, isEnabled, isVisible, isChecked, order,
            iconResource, iconDrawable, intent, onMenuClick
        )
    }

}

@MenuDSL
@Suppress("MemberVisibilityCanBePrivate")
class GroupItemBuilder {

    @IdRes
    var id: Int = 0
    var enabled: Boolean = true
    private val items = ArrayList<Item>()

    fun item(builder: ItemBuilder.() -> Unit) =
        ItemBuilder().apply(builder).also { items.add(it.build()) }

    internal fun build() = GroupItem(id, enabled, items)

}

class MenuBuilder {

    private val items = ArrayList<Item>()
    private val groups = ArrayList<GroupItem>()

    fun item(builder: ItemBuilder.() -> Unit) =
        ItemBuilder().apply(builder).also { items.add(it.build()) }

    fun group(builder: GroupItemBuilder.() -> Unit) =
        GroupItemBuilder().apply(builder).also { groups.add(it.build()) }

    internal fun build() = Menu(items, groups)

}