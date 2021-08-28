package com.aperezsi.core.state

interface Renderable<VS> {

    fun render(viewState: VS)
}