/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.wearable.composeadvanced

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.Lifecycle
import com.example.android.wearable.composeadvanced.presentation.MainActivity
import com.example.android.wearable.composeadvanced.presentation.navigation.Screen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NavActivityTest {
    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testEvent() {
        val scenario = rule.activityRule.scenario

        rule.waitForIdle()

        toListAndBack()

        scenario.moveToState(Lifecycle.State.STARTED)

        scenario.moveToState(Lifecycle.State.RESUMED)

        toListAndBack()
    }

    private fun toListAndBack() {
        assertEquals(
            Screen.Landing.route,
            rule.activity.navController.currentBackStackEntry?.destination?.route
        )

        rule.runOnUiThread {
            rule.activity.navController.navigate(Screen.WatchList.route)
        }
        rule.waitForIdle()

        assertEquals(
            Screen.WatchList.route,
            rule.activity.navController.currentBackStackEntry?.destination?.route
        )

        rule.runOnUiThread {
            rule.activity.navController.navigate(Screen.Landing.route) {
                this.popUpTo(Screen.Landing.route)
            }
        }
        rule.waitForIdle()

        assertEquals(
            Screen.Landing.route,
            rule.activity.navController.currentBackStackEntry?.destination?.route
        )
    }
}
