package com.aperezsi.diablobuddy.shared.data.converter

import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

class MoshiProvider : ConverterFactoryProvider {

    override fun build(): Converter.Factory {
        return MoshiConverterFactory.create()
    }
}