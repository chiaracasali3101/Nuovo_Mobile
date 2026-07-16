package com.unibo.android.nuovo_prog_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import android.presentation.Ricerca
import android.presentation.DettaglioFilm
import android.presentation.DettaglioViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.domain.models.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import android.home.HomeScreen
import android.screens.ProfileScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unibo.android.ui.screens.LoginScreen
import android.screens.RegisterScreen

private const val TMDB_API_KEY = "f68e046df68555567f96d4cdfcc3ffdf"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var query by remember { mutableStateOf("") }
                    var listaFilm by remember { mutableStateOf(emptyList<Film>()) }
                    var filmSelezionato by remember { mutableStateOf<Film?>(null) }
                    val scope = rememberCoroutineScope()
                    val dettaglioViewModel: DettaglioViewModel = viewModel()
                    val navController = rememberNavController()

                    // Il NavHost gestisce TUTTE le schermate
                    NavHost(navController = navController, startDestination = "login") {

                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate("home") { popUpTo("login") { inclusive = true } }
                                },
                                onNavigateToRegister = {
                                    navController.navigate("registrazione")
                                }
                            )
                        }

                        composable("registrazione") {
                            RegisterScreen(
                                onRegisterSuccess = {
                                    navController.navigate("home") { popUpTo("login") { inclusive = true } }
                                },
                                onNavigateToLogin = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("home") {
                            HomeScreen(navController = navController)
                        }

                        composable("mappa") {
                            MapScreen(navController = navController)
                        }

                        composable("profilo") {
                            ProfileScreen(navController = navController)
                        }

                        // ECCO LA TUA NUOVA ROTTA DI RICERCA!
                        composable("ricerca") {
                            // Le variabili di stato vivono solo finché sei in questa schermata
                            var query by remember { mutableStateOf("") }
                            var listaFilm by remember { mutableStateOf(emptyList<Film>()) }
                            var filmSelezionato by remember { mutableStateOf<Film?>(null) }
                            val scope = rememberCoroutineScope()

                    LaunchedEffect(query) {
                        if (query.isBlank()) {
                            listaFilm = emptyList()
                            return@LaunchedEffect
                        }
                        delay(500)
                        listaFilm = withContext(Dispatchers.IO) {
                            UseCasesProvider.useCasesRicerca(query, tmdbApiKey = TMDB_API_KEY)
                        }
                    }

                    if (filmSelezionato == null) {
                        Ricerca(
                            query = query,
                            listaFilm = listaFilm,
                            onQueryChange = { nuovoTesto -> query = nuovoTesto },
                            onMovieClick = { film -> filmSelezionato = film }
                        )
                    } else {
                        DettaglioFilm(
                            film = filmSelezionato,
                            onBack = { filmSelezionato = null },
                            onInviaRecensione = { /* ... */ },
                            onAggiungiWatchlist = {
                                dettaglioViewModel.aggiungiAllaWatchlist(filmSelezionato!!)
                            },
                            onPreferito = { nuovoValore ->
                                dettaglioViewModel.preferito(filmSelezionato!!, nuovoValore)
                            },
                            onVisto = { nuovoValore ->
                                dettaglioViewModel.impostaVisto(filmSelezionato!!, nuovoValore)
                            }
                        )
                    }
                }
            }
        }
    }
}