package com.unibo.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unibo.android.ui.R
data class Curiosita(
    val titolo: String,
    val testo: String,
    val immagine: Int
)

@Composable
fun HomeScreen() {
    val listaCuriosita = listOf(
        Curiosita(
            titolo = "1) CHI HA INVENTATO IL CINEMA?",
            testo = "Nel 1885 G. Eastman produce la pellicola, mentre la prima ripresa (3 secondi) è girata nel 1888 dal francese Louis Le Prince. Il cinema moderno nasce però nel 1895, con i fratelli Lumière.",
            immagine = R.drawable.img_lumiere
        ),
        Curiosita(
            titolo = "2) LA NASCITA DEGLI EFFETTI SPECIALI",
            testo = "Il primo film a fare ampio ricorso agli effetti speciali è uscito nel 1902. Si tratta di Viaggio nella Luna, di George Méliès: 15 minuti che raccontano la spedizione di un gruppo di astronomi terrestri fra i mostruosi Seleniti.",
            immagine = R.drawable.img_melies
        ),
        Curiosita(
            titolo = "3) HOLLYWOOD, SIMBOLO PER CASO",
            testo = "La scritta \"Hollywood\" che sovrasta Los Angeles, è davvero enorme, lunga 110 m, con lettere larghe 9 m e alte 15. È diventata il simbolo del cinema americano, ma con quel mondo ha poco a che fare: è stata eretta nel 1923, per pubblicizzare un nuovo quartiere residenziale.",
            immagine = R.drawable.img_hollywood
        ),
        Curiosita(
            titolo = "4) L'OSCAR",
            testo = "il vero nome del premio più ambito nel mondo del cinema è Academy Award of Merit. Tutti però lo ricordano con il soprannome datogli involontariamente da Margaret Herrick, segretaria dell’Academy, che quando vide la statuetta disse che assomigliava… a suo zio Oscar!",
            immagine = R.drawable.img_oscar
        ),
        Curiosita(
            titolo = "5) TRAILER",
            testo = "Vi piacciono i trailer, le anticipazioni dei film in uscita che si vedono in sala prima che inizi la proiezione vera e propria? Sono vecchi quasi quanto il cinema (il primo è del 1916) e si chiamano così (trailer in inglese significa rimorchio) perché una volta si proiettavano dopo la fine del film.",
            immagine = R.drawable.img_ticket
        )
    )

    HomeContent(listaCuriosita = listaCuriosita)
}

@Composable
fun HomeContent(listaCuriosita: List<Curiosita>) {
    val backgroundColor = Color(0xFF3B0000)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sfondo_home),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, backgroundColor),
                                startY = 350f
                            )
                        )
                )

                Column(
                    modifier = Modifier.fillMaxSize().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "WELCOME TO",
                        color = Color.White,
                        fontSize = 13.sp,
                        letterSpacing = 6.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "reView",
                        color = Color.White,
                        fontSize = 86.sp,
                        fontFamily = FontFamily.Serif
                    )

                    Text(
                        text = "dai voce alla tua visione,\nesplora quella degli altri.",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Scorri verso il basso",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

        item {
            Text(
                text = "Curiosità sul cinema...",
                color = Color.White,
                fontSize = 57.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                lineHeight = 62.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 50.dp, horizontal = 16.dp)
            )
        }

        itemsIndexed(listaCuriosita) { index, curiosita ->
            CuriositaRow(
                curiosita = curiosita,
                immagineASinistra = index % 2 == 0
            )
        }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun CuriositaRow(curiosita: Curiosita, immagineASinistra: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (immagineASinistra) {
            Image(
                painter = painterResource(id = curiosita.immagine),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .height(350.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(40.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = curiosita.titolo,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = curiosita.testo,
                    color = Color(0xFFE0E0E0),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Serif,
                    lineHeight = 26.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        } else {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = curiosita.titolo,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = curiosita.testo,
                    color = Color(0xFFE0E0E0),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Serif,
                    lineHeight = 26.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            Image(
                painter = painterResource(id = curiosita.immagine),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .height(350.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1024, heightDp = 3500)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}