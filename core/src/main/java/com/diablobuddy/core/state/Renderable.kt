package com.diablobuddy.core.state

interface Renderable<VS> {

    fun render(viewState: VS)
}