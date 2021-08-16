package com.aperezsi.diablobuddy.shared.storage

import android.content.SharedPreferences
import com.aperezsi.core.date.TimeProvider
import com.aperezsi.core.date.TimeValidator
import javax.inject.Inject

class SessionPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val timeValidator: TimeValidator,
    private val timeProvider: TimeProvider
) {

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val ACCESS_TOKEN_EXPIRES = "ACCESS_TOKEN_EXPIRES"
        private const val LAST_TIME_AUTHENTICATED = "LAST_TIME_AUTHENTICATED"
    }

    fun storeAccessToken(accessToken: String) {
        sharedPreferences.edit().putString(ACCESS_TOKEN, accessToken).apply()
    }

    fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESS_TOKEN, "").orEmpty()
    }

    fun storeAccessTokenExpires(expiresIn: Int, currentTime: Long) {
        sharedPreferences.edit().putInt(ACCESS_TOKEN_EXPIRES, expiresIn).apply()
        sharedPreferences.edit().putLong(LAST_TIME_AUTHENTICATED, currentTime).apply()
    }

    fun tokenExpiresOnLessThan(minutes: Int): Boolean {
        val tokenExpiresMax = sharedPreferences.getInt(ACCESS_TOKEN_EXPIRES, 0)
        val lastTimeAuthenticated = sharedPreferences.getLong(LAST_TIME_AUTHENTICATED, 0)

        val currentTime = timeProvider.getSeconds(timeProvider.getCurrentTime())
        val timePassed = currentTime - lastTimeAuthenticated

        return timeValidator.isTimeShorterThan((tokenExpiresMax - timePassed).toInt(), minutes)
    }
}