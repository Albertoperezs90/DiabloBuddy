package com.aperezsi.diablobuddy

import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.di.DaggerCoreFakeComponent

class DiabloBuddyTestApplication: DiabloBuddyApplication() {

    override fun buildCoreComponent(): CoreComponent {
        return DaggerCoreFakeComponent.factory().create(this)
    }
}