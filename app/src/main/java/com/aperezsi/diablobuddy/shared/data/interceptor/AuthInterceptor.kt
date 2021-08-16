package com.aperezsi.diablobuddy.shared.data.interceptor

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

interface AuthInterceptor : Interceptor {
    fun buildCredentials(): String
}

class AuthInterceptorImpl(private val username: String, private val password: String) : AuthInterceptor {

    override fun buildCredentials(): String {
        return Credentials.basic(username, password)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder().header("Authorization", buildCredentials()).build()
        return chain.proceed(authenticatedRequest)
    }

}