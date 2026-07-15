package android.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.unibo.android.domain.models.Film
import org.junit.Rule
import org.junit.Test

class RicercaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val filmFinti = listOf(
        Film(
            id = 1,
            titolo = "Batman",
            anno = "2022",
            trama = "Trama di Batman...",
            genere = "Azione",
            durata = "175 min",
            regista = "Matt Reeves",
            punteggio = 7.8,
            percorsoLocandina = "",
            preferito = false
        )
    )

    @Test
    fun testSchermataVuotaMostraMessaggio() {
        composeTestRule.setContent {
            Ricerca(
                query = "",
                listaFilm = emptyList(),
                onQueryChange = {},
                onMovieClick = {}
            )
        }

        composeTestRule.onNodeWithText("Nessun film trovato").assertIsDisplayed()
    }

    @Test
    fun testDigitazioneAggiornaQuery() {
        var queryInserita = ""
        composeTestRule.setContent {
            Ricerca(
                query = queryInserita,
                listaFilm = filmFinti,
                onQueryChange = { nuovaQuery -> queryInserita = nuovaQuery },
                onMovieClick = {}
            )
        }

        composeTestRule.onNodeWithText("Cerca un film...").performTextInput("Interstellar")

        assert(queryInserita == "Interstellar")
    }

    @Test
    fun testPulsanteCancellaSvuotaBarra() {
        var queryReset = "Inception"
        composeTestRule.setContent {
            Ricerca(
                query = queryReset,
                listaFilm = filmFinti,
                onQueryChange = { nuovaQuery -> queryReset = nuovaQuery },
                onMovieClick = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("Svuota").performClick()

        assert(queryReset.isEmpty())
    }
}