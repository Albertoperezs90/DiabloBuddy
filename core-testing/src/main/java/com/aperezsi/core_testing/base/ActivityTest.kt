package com.aperezsi.core_testing.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.test.espresso.intent.rule.IntentsTestRule
import org.junit.Rule
import kotlin.reflect.KClass

abstract class ActivityTest<T: Activity>(clazz: KClass<T>) {

    @get:Rule
    val testRule: IntentsTestRule<T> = IntentsTestRule(clazz.java, true, false)

    fun startActivity(args: Bundle = Bundle()): T {
        val intent = Intent()
        intent.putExtras(args)
        return testRule.launchActivity(intent)
    }

}