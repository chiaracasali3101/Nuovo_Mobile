package com.unibo.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.unibo.android.android.data.local.FilmEntity

@Composable
fun FilmCard(film: FilmEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${film.percorsoLocandina}",
                contentDescription = film.titolo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = film.titolo,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp),
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun FilmCardPreview(){
    FilmCard(FilmEntity(id = 0, titolo = "prova", anno = "2010", trama = "jjj", genere = "horror", durata = "120", regista = "titi", punteggio = 10.0, percorsoLocandina = "jjjj" )) { }
}