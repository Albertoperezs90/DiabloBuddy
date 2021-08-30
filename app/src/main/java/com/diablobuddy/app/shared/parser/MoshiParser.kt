package com.diablobuddy.app.shared.parser

import com.diablobuddy.core.interfaces.parser.JsonParser
import com.squareup.moshi.Moshi
import kotlin.reflect.KClass

class MoshiParser : JsonParser {

    private val moshi = Moshi.Builder().build()

    override fun <T : Any> encode(value: T): String {
        val jsonAdapter = moshi.adapter<T>(value::class.java)
        return jsonAdapter.toJson(value)
    }

    override fun <T : Any> decode(json: String, type: KClass<T>): T? {
        val jsonAdapter = moshi.adapter<T>(type.java)
        return jsonAdapter.fromJson(json)
    }
}