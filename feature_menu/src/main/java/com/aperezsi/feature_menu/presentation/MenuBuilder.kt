package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.views.CircularItemConfig
import com.aperezsi.core.views.CircularMenuConfig
import com.aperezsi.feature_menu.R
import javax.inject.Inject

class MenuBuilder @Inject constructor() {

    private val items = mutableListOf<MenuItem>()

    fun add(menuItem: MenuItem): MenuBuilder {
        items.add(menuItem)
        return this
    }

    fun build(): CircularMenuConfig {
        return CircularMenuConfig(R.drawable.ic_central_icon, items.map { resolveItem(it) })
    }

    private fun resolveItem(menuItem: MenuItem): CircularItemConfig {
        return when (menuItem) {
            MenuItem.SKILLS -> CircularItemConfig(R.drawable.ic_skills, R.string.skills)
            MenuItem.GEAR -> CircularItemConfig(R.drawable.ic_gear, R.string.gear)
            MenuItem.CRAFTING -> CircularItemConfig(R.drawable.ic_crafting, R.string.crafting)
            MenuItem.FOLLOWER -> CircularItemConfig(R.drawable.ic_follower, R.string.follower)
        }
    }
}

enum class MenuItem { SKILLS, GEAR, CRAFTING, FOLLOWER }