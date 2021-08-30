package com.diablobuddy.app.shared.storage

import android.content.SharedPreferences
import com.diablobuddy.core.utilities.time.TimeProvider
import com.diablobuddy.core.utilities.time.TimeValidator
import javax.inject.Inject

class AppPreferences @Inject constructor(
    private val appPreferences: SharedPreferences,
    private val timeValidator: TimeValidator,
    private val timeProvider: TimeProvider
) {

    companion object {

        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val ACCESS_TOKEN_EXPIRES = "ACCESS_TOKEN_EXPIRES"
        private const val LAST_TIME_AUTHENTICATED = "LAST_TIME_AUTHENTICATED"
        private const val SEASON_INDEX = "SEASON_INDEX"

        private const val MILISECONDS_TO_SECONDS = 1000
    }

    fun storeAccessToken(accessToken: String) {
        appPreferences.edit().putString(ACCESS_TOKEN, accessToken).apply()
    }

    fun getAccessToken(): String {
        return appPreferences.getString(ACCESS_TOKEN, "").orEmpty()
    }

    fun storeAccessTokenExpires(expiresIn: Int, currentTime: Long) {
        appPreferences.edit().putInt(ACCESS_TOKEN_EXPIRES, expiresIn).apply()
        appPreferences.edit().putLong(LAST_TIME_AUTHENTICATED, currentTime).apply()
    }

    fun tokenExpiresOnLessThan(minutes: Int): Boolean {
        val tokenExpiresMax = appPreferences.getInt(ACCESS_TOKEN_EXPIRES, 0)
        val lastTimeAuthenticated = appPreferences.getLong(LAST_TIME_AUTHENTICATED, 0)

        val currentTime = timeProvider.getCurrentTime() / MILISECONDS_TO_SECONDS
        val timePassed = currentTime - lastTimeAuthenticated

        return timeValidator.isTimeShorterThan((tokenExpiresMax - timePassed).toInt(), minutes)
    }

    fun setSeasonIndex(index: Int) {
        appPreferences.edit().putInt(SEASON_INDEX, index).apply()
    }

    fun getSeasonIndex(): Int {
        return appPreferences.getInt(SEASON_INDEX, 0)
    }
}