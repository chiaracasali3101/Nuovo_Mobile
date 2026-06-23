package com.unibo.android.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.unibo.android.corsolp2526.data.model.MovieDto
import com.unibo.android.ui.leaderboard.ClassificaContent
import org.junit.Rule
import org.junit.Test

class ClassificaScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun classifica_mostraCorrettamenteIDatiA_Schermo() {
        val fakeMovies = listOf(
            MovieDto(
                id = 1,
                title = "Il Padrino",
                overview = "",
                posterPath = null,
                voteAverage = 9.2,
                releaseDate = "1972"
            ),
            MovieDto(id = 2, title = "Pulp Fiction", overview = "", posterPath = null, voteAverage = 8.9, releaseDate = "1994")
        )

        composeTestRule.setContent {
            ClassificaContent(listaFilm = fakeMovies)
        }

        composeTestRule.onNodeWithText("Top Rated").assertIsDisplayed()
        composeTestRule.onNodeWithText("Il Padrino").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pulp Fiction").assertIsDisplayed()
    }
}