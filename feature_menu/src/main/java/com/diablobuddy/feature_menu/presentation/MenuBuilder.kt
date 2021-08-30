package com.diablobuddy.feature_menu.presentation

import com.diablobuddy.core.views.CircularItemConfig
import com.diablobuddy.core.views.CircularMenuConfig
import com.diablobuddy.feature_menu.R
import javax.inject.Inject

class MenuBuilder @Inject constructor() {

    private val items = mutableListOf<MenuItem>()

    fun add(menuItem: MenuItem): MenuBuilder {
        items.add(menuItem)
        return this
    }

    fun build(): CircularMenuConfig {
        val circularItems = items.map { resolveItem(it) }
        items.clear()
        return CircularMenuConfig(R.drawable.ic_central_icon, circularItems)
    }

    private fun resolveItem(menuItem: MenuItem): CircularItemConfig {
        return when (menuItem) {
            is MenuItem.Skills -> CircularItemConfig(R.drawable.ic_skills, R.string.skills, menuItem.onClick)
            MenuItem.Gear -> CircularItemConfig(R.drawable.ic_gear, R.string.gear)
            MenuItem.Crafting -> CircularItemConfig(R.drawable.ic_crafting, R.string.crafting)
            MenuItem.Follower -> CircularItemConfig(R.drawable.ic_follower, R.string.follower)
        }
    }
}

sealed class MenuItem {
    class Skills(val onClick: () -> Unit): MenuItem()
    object Gear: MenuItem()
    object Crafting: MenuItem()
    object Follower: MenuItem()
}