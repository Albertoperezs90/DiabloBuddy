package com.diablobuddy.core.exception

import java.io.IOException

data class NetworkException(val httpCode: Int, val url: String, val exception: Exception): IOException()