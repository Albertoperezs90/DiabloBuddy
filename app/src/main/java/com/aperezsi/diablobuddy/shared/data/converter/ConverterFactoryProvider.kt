package com.aperezsi.diablobuddy.shared.data.converter

import retrofit2.Converter

interface ConverterFactoryProvider {
    fun build(): Converter.Factory
}