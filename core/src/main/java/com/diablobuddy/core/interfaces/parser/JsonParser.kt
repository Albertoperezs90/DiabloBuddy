package com.diablobuddy.core.interfaces.parser

import kotlin.reflect.KClass

interface JsonParser {
    fun <T : Any> encode(value: T): String
    fun <T : Any> decode(json: String, type: KClass<T>): T?
}