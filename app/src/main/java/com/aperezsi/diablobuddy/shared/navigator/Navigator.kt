package com.aperezsi.diablobuddy.shared.navigator

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.diablobuddy.R
import javax.inject.Inject

class Navigator @Inject constructor(private val activity: BaseActivity<*, *, *>) {

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