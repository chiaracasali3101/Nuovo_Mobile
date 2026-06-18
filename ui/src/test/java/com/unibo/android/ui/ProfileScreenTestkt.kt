package com.unibo.android.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.unibo.android.ui.screens.ProfileScreen
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileScreen_showsUserInfo() {
        composeTestRule.setContent {
            ProfileScreen()
        }

        composeTestRule.onNodeWithText("IL TUO PROFILO").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ana").assertIsDisplayed()
        composeTestRule.onNodeWithText("ana.systemexpert@unibo.it").assertIsDisplayed()
    }
}