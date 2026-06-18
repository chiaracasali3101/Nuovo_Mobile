package com.unibo.android.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.unibo.android.ui.home.Curiosita
import com.unibo.android.ui.home.HomeContent
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun home_mostraTitoloPrincipale() {
        val fakeCuriosita = listOf(
            Curiosita(
                titolo = "TITOLO DI PROVA",
                testo = "Testo di prova",
                immagine = R.drawable.img_lumiere
            )
        )

        composeTestRule.setContent {
            HomeContent(listaCuriosita = fakeCuriosita)
        }

        composeTestRule.onNodeWithText("WELCOME TO").assertIsDisplayed()
        composeTestRule.onNodeWithText("reView").assertIsDisplayed()
        composeTestRule.onNodeWithText("dai voce alla tua visione,\nesplora quella degli altri.").assertIsDisplayed()
    }
}