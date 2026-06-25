package com.unibo.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.unibo.android.android.data.local.FilmEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DettaglioFilm(film: FilmEntity, onBack: () -> Unit) {
    Scaffold(
        containerColor = DeepMaroon,
        topBar = {
            Surface(
                color = DeepMaroon,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = DeepMaroon,
                        titleContentColor = WarmCream,
                        navigationIconContentColor = WarmCream
                    ),
                    title = { Text("Dettaglio Film") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Filled.ArrowBack,
                                contentDescription = "Torna indietro"
                            )
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // immagine
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${film.percorsoLocandina}",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                // titolo
                Text(
                    text = film.titolo,
                    style = MaterialTheme.typography.headlineMedium,
                    color = WarmCream
                )

                Spacer(modifier = Modifier.height(16.dp))

                // riga dati
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = BoldRed,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${film.punteggio} / 10",
                        color = WarmCream,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "•", color = LightMutedCream)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = film.anno, color = LightMutedCream)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "•", color = LightMutedCream)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = film.durata, color = LightMutedCream)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // trama
                Text(
                    text = "TRAMA",
                    style = MaterialTheme.typography.titleSmall,
                    color = LightMutedCream
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = film.trama,
                    style = MaterialTheme.typography.bodyLarge,
                    color = WarmCream,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                // bottone
                Button(
                    onClick = { /* Azione per salvare */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BoldRed,
                        contentColor = WarmCream
                    )
                ) {
                    Text(
                        text = "AGGIUNGI ALLA WATCHLIST",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DettaglioFilmPreview() {
    DettaglioFilm(
        film = FilmEntity(
            id = 1,
            titolo = "Inception",
            anno = "2010",
            trama = "Un ladro professionista che si infiltra nei segreti delle persone attraverso il mondo dei sogni deve compiere l'operazione inversa: innestare un'idea.",
            genere = "Fantascienza",
            durata = "148 min",
            regista = "Christopher Nolan",
            punteggio = 8.8,
            percorsoLocandina = "",
            preferito = false
        ),
        onBack = {  }
    )
}