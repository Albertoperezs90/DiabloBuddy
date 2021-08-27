package com.aperezsi.diablobuddy.module.splash.presentation

import com.aperezsi.core_testing.base.ActivityTest
import com.karumi.shot.ScreenshotTest
import org.junit.Test

class SplashActivityTest: ActivityTest<SplashActivity>(SplashActivity::class), ScreenshotTest {

    @Test
    fun firstScreenShotTest() {
        val splashActivity = startActivity()

        compareScreenshot(splashActivity)
    }
}