package com.unibo.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibo.android.android.data.local.FilmEntity
import com.unibo.android.ui.components.FilmCard

val DeepMaroon = Color(0xFF1C0909)
val WarmCream = Color(0xFFF3F0DF)
val BoldRed = Color(0xFFB22222)
val LightMutedCream = Color(0xFFD1B08C)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ricerca(
    query: String,
    listaFilm: List<FilmEntity>,
    onQueryChange: (String) -> Unit,
    onMovieClick: (FilmEntity) -> Unit
) {
    Scaffold(
        containerColor = DeepMaroon,
        topBar = {
            Surface(
                color = DeepMaroon,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { nuovoTesto -> onQueryChange(nuovoTesto) },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Cerca un film...", color = LightMutedCream) },
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium, // Arrotondato coerente con il vostro profilo
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search, // Icona lente d'ingrandimento corretta
                                contentDescription = "Cerca",
                                tint = LightMutedCream
                            )
                        },
                        trailingIcon = {
                            if (query.isNotEmpty()) {
                                IconButton(onClick = { onQueryChange("") }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Svuota",
                                        tint = BoldRed
                                    )
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BoldRed,
                            unfocusedBorderColor = LightMutedCream.copy(alpha = 0.5f),
                            cursorColor = BoldRed,
                            focusedTextColor = WarmCream,
                            unfocusedTextColor = WarmCream,
                            focusedContainerColor = DeepMaroon,
                            unfocusedContainerColor = DeepMaroon
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        if (listaFilm.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nessun film trovato",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightMutedCream
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = 8.dp) // Dà respiro all'inizio e alla fine della lista
            ) {
                items(listaFilm) { film ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        FilmCard(
                            film = film,
                            onClick = { onMovieClick(film) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RicercaPreview() {
    val filmFinti = listOf(
        FilmEntity(
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
        ),
        FilmEntity(
            id = 2,
            titolo = "Interstellar",
            anno = "2014",
            trama = "Trama di Interstellar...",
            genere = "Sci-Fi",
            durata = "169 min",
            regista = "C. Nolan",
            punteggio = 8.6,
            percorsoLocandina = "",
            preferito = true
        )
    )

    Ricerca(
        query = "Batman",
        listaFilm = filmFinti,
        onQueryChange = { },
        onMovieClick = { }
    )
}