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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import android.presentation.Ricerca
import android.presentation.DettaglioFilm
import android.presentation.DettaglioViewModel
import android.presentation.RicercaViewModel
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.domain.models.Film
import android.home.HomeScreen
import android.leaderboard.ClassificaViewModel
import android.leaderboard.ClassificaViewModelFactory
import android.screens.MapScreen
import android.screens.ProfileScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unibo.android.ui.screens.LoginScreen
import android.screens.RegisterScreen
import com.unibo.android.ui.leaderboard.ClassificaScreen

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
                    val dettaglioViewModel: DettaglioViewModel = viewModel()
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {

                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onNavigateToRegister = {
                                    navController.navigate("registrazione")
                                }
                            )
                        }

                        composable("registrazione") {
                            RegisterScreen(
                                onRegisterSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
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
                            val repository = UseCasesProvider.getRepository()
                            val profileViewModel: android.screens.ProfileViewModel = viewModel(
                                factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                                    @Suppress("UNCHECKED_CAST")
                                    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                                        return android.screens.ProfileViewModel(repository as com.unibo.android.domain.repositories.FilmRepository) as T
                                    }
                                }
                            )
                            ProfileScreen(navController = navController, viewModel = profileViewModel)
                        }

                        composable("classifica") {
                            val repository = UseCasesProvider.getRepository()
                            val classificaViewModel: ClassificaViewModel = viewModel(
                                factory = ClassificaViewModelFactory(repository)
                            )

                            var filmSelezionato by remember { mutableStateOf<Film?>(null) }
                            val currentFilm = filmSelezionato

                            if (currentFilm == null) {
                                ClassificaScreen(
                                    viewModel = classificaViewModel,
                                    onMovieClick = { film -> filmSelezionato = film }
                                )
                            } else {
                                DettaglioFilm(
                                    film = currentFilm,
                                    onBack = { filmSelezionato = null },
                                    onInviaRecensione = { /* Gestisci recensione se prevista */ },
                                    onAggiungiWatchlist = { dettaglioViewModel.aggiungiAllaWatchlist(currentFilm) },
                                    onPreferito = { nuovoValore -> dettaglioViewModel.preferito(currentFilm, nuovoValore) },
                                    onVisto = { nuovoValore -> dettaglioViewModel.impostaVisto(currentFilm, nuovoValore) }
                                )
                            }
                        }

                        composable("ricerca") {
                            val ricercaViewModel: RicercaViewModel = viewModel()
                            val query by ricercaViewModel.query.collectAsState()
                            val listaFilm by ricercaViewModel.listaFilm.collectAsState()
                            var filmSelezionato by remember { mutableStateOf<Film?>(null) }
                            val currentFilm = filmSelezionato

                            if (currentFilm == null) {
                                Ricerca(
                                    query = query,
                                    listaFilm = listaFilm,
                                    onQueryChange = { nuovoTesto -> ricercaViewModel.aggiornaQuery(nuovoTesto) },
                                    onMovieClick = { film -> filmSelezionato = film }
                                )
                            } else {
                                DettaglioFilm(
                                    film = currentFilm,
                                    onBack = { filmSelezionato = null },
                                    onInviaRecensione = { /* Gestisci recensione se prevista */ },
                                    onAggiungiWatchlist = { dettaglioViewModel.aggiungiAllaWatchlist(currentFilm) },
                                    onPreferito = { nuovoValore -> dettaglioViewModel.preferito(currentFilm, nuovoValore) },
                                    onVisto = { nuovoValore -> dettaglioViewModel.impostaVisto(currentFilm, nuovoValore) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}