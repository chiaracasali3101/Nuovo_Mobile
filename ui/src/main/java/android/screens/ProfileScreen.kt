package com.unibo.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unibo.android.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val sfondoMarrone = Color(0xFF210100)
    val coloreCrema = Color(0xFFFECE79)
    val coloreOro = Color(0xFFE6A341)
    val coloreRossoTesto = Color(0xFFB14A36)
    val coloreRossoBottoni = Color(0xFF8C0902)

    val listaFilmVisti = listOf(
        Pair("Inception", "8.5"), Pair("Interstellar", "8.5"), Pair("Pulp Fiction", "9.0"),
        Pair("WinterMarter", "8.5"), Pair("Sal:lisare", "8.5"), Pair("The Prestige", "8.8")
    )
    val listaWatchlist = listOf("Dune: Part Two", "Oppenheimer", "The Batman", "Blade Runner 2049", "Gladiator 2")

    Box(modifier = Modifier.fillMaxSize().background(sfondoMarrone)) {

        // --- SFONDO SFUMATO ---
        Box(modifier = Modifier.fillMaxWidth().height(420.dp)) {
            Image(
                painter = painterResource(id = R.drawable.sfondo_profilo),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize().background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, sfondoMarrone.copy(alpha = 0.5f), sfondoMarrone)
                    )
                )
            )
        }

        // --- CONTENUTO IN COLONNA REGOLE ---
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(text = "re", color = coloreCrema, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "View", color = Color.Red, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "IL TUO PROFILO", color = coloreCrema, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            Spacer(modifier = Modifier.height(45.dp))

            // --- FOTO PROFILO (Semplice, non serve più cliccarla) ---
            Box(
                modifier = Modifier.size(120.dp).clip(CircleShape).background(sfondoMarrone).border(3.dp, coloreOro, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(90.dp), tint = coloreCrema.copy(alpha = 0.8f))
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Ana", color = coloreCrema, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = "ana.systemexpert@unibo.it", color = coloreCrema.copy(alpha = 0.6f), fontSize = 14.sp)

            Spacer(modifier = Modifier.height(25.dp))

            // --- 🛠️ NUOVO BLOCCO FISSO: OPZIONI IMMAGINE DEL PROFILO ---
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF3A0605).copy(alpha = 0.7f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    Text(
                        text = "Immagine del profilo",
                        color = coloreCrema,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Tasto 1: Fotocamera
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable { /* Logica scatta foto */ }.padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.CameraAlt, contentDescription = null, tint = coloreRossoTesto, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Scatta una foto direttamente", color = coloreCrema, fontSize = 14.sp)
                    }

                    // Linea divisoria interna
                    HorizontalDivider(color = coloreCrema.copy(alpha = 0.1f), thickness = 1.dp)

                    // Tasto 2: Galleria
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable { /* Logica galleria */ }.padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.PhotoLibrary, contentDescription = null, tint = coloreRossoTesto, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Seleziona dalla galleria", color = coloreCrema, fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // --- STATISTICHE ---
            Row(modifier = Modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                StatisticCard(Modifier.weight(1f), "52", "Film Visti", coloreRossoBottoni, coloreCrema)
                StatisticCard(Modifier.weight(1f), "14", "Recensioni", coloreRossoBottoni, coloreCrema)
            }

            Spacer(modifier = Modifier.height(25.dp))

            // --- BOTTONE CERCA SALE ---
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(0.9f).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = coloreRossoBottoni),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("CERCA SALE VICINE", color = coloreCrema, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(10.dp))
                Icon(Icons.Default.LocationOn, null, tint = coloreCrema)
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- LISTE FILM RECENTI E WATCHLIST ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    SectionTitle("FILM RECENTI")
                    listaFilmVisti.forEach { MovieItem(it.first, it.second, Icons.Default.Star, Color.Red, coloreCrema, coloreRossoTesto) }
                }
                Column(modifier = Modifier.weight(1f)) {
                    SectionTitle("WATCHLIST")
                    listaWatchlist.forEach { MovieItem(it, "Da vedere", Icons.Default.Bookmark, coloreOro, coloreCrema, coloreRossoTesto) }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun StatisticCard(modifier: Modifier, value: String, label: String, bgColor: Color, textColor: Color) {
    Card(
        modifier = modifier.height(65.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor.copy(alpha = 0.7f)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = value, color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = label, color = textColor, fontSize = 12.sp)
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(text = text, color = Color(0xFFFECE79), fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun MovieItem(title: String, subtitle: String, icon: ImageVector, iconColor: Color, textColor: Color, subColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3A0605).copy(alpha = 0.6f)),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(text = title, color = textColor, fontSize = 13.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    if (subtitle != "Da vedere") Text(text = "20/05/2026", color = Color.Gray, fontSize = 9.sp)
                }
            }
            Text(text = subtitle, color = subColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ProfilePreview() {
    ProfileScreen()
}