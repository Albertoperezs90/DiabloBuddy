package com.aperezsi.diablobuddy.shared.data.interceptor

import com.aperezsi.core.interfaces.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class LoggingInterceptor @Inject constructor(private val logger: Logger): Interceptor {

    private val NETWORK_CALL = "NETWORK_CALL"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        logger.debug(NETWORK_CALL, "Request: ${request.url()}")
        val headers = request.headers().toMultimap().map {
            "\n${it.key} -> ${it.value}"
        }
        logger.debug(NETWORK_CALL, "Headers: $headers")

        val response = chain.proceed(request)
        response.body().run {
            val contentType = this?.contentType()
            val body = this?.string()
            logger.debug(NETWORK_CALL, "Response: ${response.code()} - ${response.request().url()} -> $body")
            val newResponse = ResponseBody.create(contentType, body.orEmpty())
            return response.newBuilder().body(newResponse).build()
        }
    }
}