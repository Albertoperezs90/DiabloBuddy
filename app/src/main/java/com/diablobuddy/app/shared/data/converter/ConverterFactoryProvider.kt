package com.diablobuddy.app.shared.data.converter

import retrofit2.Converter

interface ConverterFactoryProvider {
    fun build(): Converter.Factory
}