package android.screens

import android.components.ReViewBottomBar
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unibo.android.ui.R
import androidx.compose.ui.res.vectorResource
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: androidx.navigation.NavController,
    viewModel: ProfileViewModel
) {
    val sfondoMarrone = Color(0xFF210100)
    val coloreCrema = Color(0xFFFECE79)
    val coloreOro = Color(0xFFE6A341)
    val coloreRossoTesto = Color(0xFFB14A36)
    val coloreRossoBottoni = Color(0xFF8C0902)

    val listaFilmVisti by viewModel.filmVisti.collectAsState(initial = emptyList())
    val listaWatchlist by viewModel.watchlist.collectAsState(initial = emptyList())

    //Vengono lette le informazioni inserite dal viewmodel
    val nomeUtente by viewModel.nomeUtente.collectAsState(initial = "Utente")
    val emailUtente by viewModel.emailUtente.collectAsState(initial = "Caricamento...")

    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var profileImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    //Gestione galleria e fotocamera
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                profileImageUri = uri
                profileImageBitmap = null
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            if (bitmap != null) {
                profileImageBitmap = bitmap
                profileImageUri = null
            }
        }
    )

    Scaffold(
        bottomBar = { ReViewBottomBar(navController = navController) },
        containerColor = Color.Transparent
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize().background(sfondoMarrone)) {

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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(bottom = innerPadding.calculateBottomPadding()),
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

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(sfondoMarrone)
                        .border(3.dp, coloreOro, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    //Qui viene gestito il cerchio del profilo: la foto viene scattata subito o sennò viene presa dalla galleria
                    if (profileImageBitmap != null) {
                        Image(
                            bitmap = profileImageBitmap!!.asImageBitmap(),
                            contentDescription = "Avatar Profilo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else if (profileImageUri != null) {
                        AsyncImage(
                            model = profileImageUri,
                            contentDescription = "Avatar Profilo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(90.dp),
                            tint = coloreCrema.copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))
                // MODIFICA 2: Usiamo le variabili al posto del testo fisso
                Text(text = nomeUtente, color = coloreCrema, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(text = emailUtente, color = coloreCrema.copy(alpha = 0.6f), fontSize = 14.sp)

                Spacer(modifier = Modifier.height(25.dp))

                // creazione del riquadro con i pulsanti funzionanti
                Card(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF3A0605).copy(alpha = 0.7f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                        Text(text = "Immagine del profilo", color = coloreCrema, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { cameraLauncher.launch(null) }
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_camera), contentDescription = null, tint = coloreRossoTesto, modifier = Modifier.size(22.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Scatta una foto direttamente", color = coloreCrema, fontSize = 14.sp)
                        }

                        HorizontalDivider(color = coloreCrema.copy(alpha = 0.1f), thickness = 1.dp)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                }
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_photolibrary), contentDescription = null, tint = coloreRossoTesto, modifier = Modifier.size(22.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Seleziona dalla galleria", color = coloreCrema, fontSize = 14.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                // calcolo sul totale dei film visti in modo da creare le statistiche
                Row(modifier = Modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    StatisticCard(Modifier.weight(1f), "${listaFilmVisti.size}", "Film Visti", coloreRossoBottoni, coloreCrema)
                    StatisticCard(Modifier.weight(1f), "0", "Recensioni", coloreRossoBottoni, coloreCrema)
                }

                Spacer(modifier = Modifier.height(25.dp))

                // definisce il pulsante interattivo di cerca sale vicine
                Button(
                    onClick = { navController.navigate("mappa") },
                    modifier = Modifier.fillMaxWidth(0.9f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = coloreRossoBottoni),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("CERCA SALE VICINE", color = coloreCrema, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.width(10.dp))
                    Icon(Icons.Default.LocationOn, null, tint = coloreCrema)
                }

                Spacer(modifier = Modifier.height(40.dp))

                // vengono popolate le liste dei film recenti e della watchlist
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        SectionTitle("FILM RECENTI")
                        listaFilmVisti.forEach { film ->
                            MovieItem(
                                title = film.titolo ?: "Sconosciuto",
                                subtitle = film.punteggio.toString(),
                                icon = Icons.Default.Star,
                                iconColor = Color.Red,
                                textColor = coloreCrema,
                                subColor = coloreRossoTesto
                            )
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        SectionTitle("WATCHLIST")
                        listaWatchlist.forEach { film ->
                            MovieItem(
                                title = film.titolo ?: "Sconosciuto",
                                subtitle = "Da vedere",
                                icon = ImageVector.vectorResource(id = R.drawable.ic_bookmark),
                                iconColor = coloreOro,
                                textColor = coloreCrema,
                                subColor = coloreRossoTesto
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
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
                    if (subtitle != "Da vedere") Text(text = "Recente", color = Color.Gray, fontSize = 9.sp)
                }
            }
            Text(text = subtitle, color = subColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}