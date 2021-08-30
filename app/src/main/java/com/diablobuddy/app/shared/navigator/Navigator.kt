package com.diablobuddy.app.shared.navigator

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.diablobuddy.app.R
import com.diablobuddy.core.framework.base.BaseActivity
import javax.inject.Inject

class Navigator @Inject constructor(private val activity: BaseActivity<*, *, *>) {

    fun navigateDeeplink(deeplink: String) {
        val navDeepLinkRequest = NavDeepLinkRequest.Builder.fromUri(Uri.parse(deeplink)).build()
        activity.findNavController(R.id.navHostFragment).navigate(navDeepLinkRequest)
    }

    fun navigate(activity: Class<out Activity>, clearTask: Boolean = false) {
        val activityOptions = ActivityOptionsCompat.makeCustomAnimation(this.activity.applicationContext, R.anim.right_in, R.anim.left_out)
        if (clearTask) {
            this.activity.startActivity(
                Intent(this.activity, activity).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK), activityOptions.toBundle()
            )
        } else {
            this.activity.startActivity(
                Intent(this.activity, activity), activityOptions.toBundle()
            )
        }
    }
}