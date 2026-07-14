package com.unibo.android.corsolp2526

import android.os.Bundle
import android.screens.MapScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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