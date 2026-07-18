/*package android.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.unibo.android.domain.models.Film

@Composable
fun FilmCard(film: Film, onClick: () -> Unit) {
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
            film.titolo?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun FilmCardPreview(){
    FilmCard(Film(id = 0, titolo = "prova", anno = "2010", trama = "jjj", genere = "horror", durata = "120", regista = "titi", punteggio = 10.0, percorsoLocandina = "/../../invalid_path_to_skip_network" )) { }
}*/

package android.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.unibo.android.domain.models.Film

@Composable
fun FilmCard(film: Film, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = BoldRed.copy(alpha = 0.15f),
            contentColor = WarmCream
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            AsyncImage(
                // se il percorso è vuoto o non valido, carichiamo una stringa pulita
                model = if (film.percorsoLocandina.isNullOrBlank()) null else "https://image.tmdb.org/t/p/w500${film.percorsoLocandina}",
                contentDescription = film.titolo,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_menu_gallery),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            film.titolo?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    color = WarmCream,
                    modifier = Modifier.padding(12.dp),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun FilmCardPreview(){
    FilmCard(
        film = Film(
            id = 0,
            titolo = "Inception",
            anno = "2010",
            trama = "jjj",
            genere = "horror",
            durata = "120",
            regista = "titi",
            punteggio = 10.0,
            percorsoLocandina = ""
        ),
        onClick = { }
    )
}