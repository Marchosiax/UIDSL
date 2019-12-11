package com.peyman.uidsllib.menu

import androidx.annotation.IdRes
import com.peyman.uidsllib.MenuDSL

@MenuDSL
class ItemBuilder {

    @IdRes
    var id: Int = 0
    var title: String? = null
    var enabled: Boolean = true
    var order = 0

    internal fun build(): Item {
        if (title == null)
            throw NullPointerException("Title cannot be null")

        return Item(id, title!!, enabled, order)
    }

}

@MenuDSL
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