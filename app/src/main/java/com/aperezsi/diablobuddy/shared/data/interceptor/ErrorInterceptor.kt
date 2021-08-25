package com.aperezsi.diablobuddy.shared.data.interceptor

import com.aperezsi.core.exception.NetworkException
import com.aperezsi.core.exception.UnknownException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ErrorInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val response = chain.proceed(chain.request())
            if (!response.isSuccessful) {
                throw NetworkException(response.code(), response.request().url().toString(), Exception("Response error"))
            } else {
                response
            }
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (e: IOException) {
            throw UnknownException(e)
        }
    }
}