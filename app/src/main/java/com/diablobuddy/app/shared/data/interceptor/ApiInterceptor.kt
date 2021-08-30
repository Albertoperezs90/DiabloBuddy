package com.diablobuddy.app.shared.data.interceptor

import com.diablobuddy.app.shared.storage.AppPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(private val appPreferences: AppPreferences): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = addAccessTokenIfMissing(chain.request())

        return chain.proceed(request)
    }

    private fun addAccessTokenIfMissing(request: Request): Request {
        var currentUrl = request.url()

        if (currentUrl.queryParameter("access_token") == null) {
            val newUrl = request.url().newBuilder()
            newUrl.addQueryParameter("access_token", appPreferences.getAccessToken())
            currentUrl = newUrl.build()
        }

        return Request.Builder().url(currentUrl).build()
    }
}