package com.unibo.android.corsolp2526

import android.os.Bundle
import android.screens.MapScreen
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
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.domain.models.Film
import com.unibo.android.nuovo_prog_mobile.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.home.HomeScreen

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.unibo.android.ui.screens.LoginScreen
// Ricordati di importare la tua Home quando l'avrai creata
// import com.unibo.android.corsolp2526.screens.HomeScreen

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

                    LaunchedEffect(query) {
                        listaFilm = withContext(Dispatchers.IO) {
                            UseCasesProvider.useCasesRicerca(query, BuildConfig.TMDB_API_KEY)
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
                            film = filmSelezionato!!,
                            onBack = { filmSelezionato = null },
                            onInviaRecensione = { },
                            onAggiungiWatchlist = {
                                scope.launch(Dispatchers.IO) {
                                    UseCasesProvider.useCasesWatchlist(filmSelezionato!!)
                                }
                            }
                        )
                    }
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {

                        // --- 1. SCHERMATA DI LOGIN ---
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    // Adesso ACCEDI ti porta alla Home!
                                    navController.navigate("home") {
                                        // TRUCCO PRO: Cancella il login dalla cronologia.
                                        // Così, se l'utente preme "Indietro" dalla Home, l'app si chiude
                                        // invece di tornare stranamente alla schermata di login.
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onNavigateToRegister = {
                                    println("Hai cliccato REGISTRATI!")
                                }
                            )
                        }

                        // --- 2. SCHERMATA PRINCIPALE (con Navbar) ---
                        composable("home") {
                            HomeScreen(
                                onNavigateToMap = {
                                    navController.navigate("mappa")
                                }
                            )
                        }

                        // --- 3. LA MAPPA ---
                        // Rimane registrata qui nel navigatore globale.
                        // Quando sarai nel Profilo, ti basterà fare navController.navigate("mappa")
                        composable("mappa") {
                            MapScreen()
                        }
                    }
                }
            }
        }
    }
}