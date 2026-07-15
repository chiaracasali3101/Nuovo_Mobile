package android.presentation

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.unibo.android.domain.models.Film
import org.junit.Rule
import org.junit.Test

class DettaglioFilmTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fintoFilm = Film(
        id = 1,
        titolo = "Inception",
        anno = "2010",
        trama = "Un ladro professionista...",
        genere = "Fantascienza",
        durata = "148 min",
        regista = "Christopher Nolan",
        punteggio = 8.8,
        percorsoLocandina = "",
        preferito = false
    )

    @Test
    fun testPulsanteInviaDisabilitatoSeVuoto() {
        composeTestRule.setContent {
            DettaglioFilm(
                film = fintoFilm,
                onBack = {},
                onInviaRecensione = {},
                onAggiungiWatchlist = {}
            )
        }


        composeTestRule.onNodeWithText("INVIA").assertIsNotEnabled()
    }

    @Test
    fun testPulsanteInviaAbilitatoSeScrivoTesto() {
        composeTestRule.setContent {
            DettaglioFilm(
                film = fintoFilm,
                onBack = {},
                onInviaRecensione = {},
                onAggiungiWatchlist = {}
            )
        }

        composeTestRule.onNodeWithText("Scrivi cosa ne pensi di questo film...")
            .performTextInput("Capolavoro assoluto!")

        composeTestRule.onNodeWithText("INVIA").assertIsEnabled()
    }

    @Test
    fun testInvioRecensioneSvuotaCampo() {
        var recensioneInviata = ""
        composeTestRule.setContent {
            DettaglioFilm(
                film = fintoFilm,
                onBack = {},
                onInviaRecensione = { testo -> recensioneInviata = testo },
                onAggiungiWatchlist = {}
            )
        }

        composeTestRule.onNodeWithText("Scrivi cosa ne pensi di questo film...")
            .performTextInput("Bello!")

        composeTestRule.onNodeWithText("INVIA").performClick()

        assert(recensioneInviata == "Bello!")

        composeTestRule.onNodeWithText("Scrivi cosa ne pensi di questo film...").assertExists()
    }
}