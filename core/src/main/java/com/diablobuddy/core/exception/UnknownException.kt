package com.diablobuddy.core.exception

import java.io.IOException

data class UnknownException(val exception: Exception): IOException()