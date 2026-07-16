package android.screens

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import com.unibo.android.ui.R
import android.components.ReViewBottomBar
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MapScreen(navController: NavController) {
    val isPreview = LocalInspectionMode.current
    val context = LocalContext.current
    val locationPermissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)
        val coarseLocationGranted = permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)

        if (fineLocationGranted || coarseLocationGranted) {
            Toast.makeText(context, "Posizione attivata! Cerco i cinema...", Toast.LENGTH_SHORT).show()
            // TODO: In futuro qui aggiungeremo la logica per centrare la mappa sulla posizione dell'utente
        } else {
            Toast.makeText(context, "Permesso negato. Impossibile trovare i cinema vicini.", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = { ReViewBottomBar(navController = navController) }
    ) { innerPadding ->
        if (isPreview) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFF210100)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFFFECE79),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Mappa Disattivata in Preview", color = Color(0xFFFECE79))
                    Text(text = "Avvia l'emulatore per vederla", color = Color.Gray)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        Configuration.getInstance().load(ctx, ctx.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
                        Configuration.getInstance().userAgentValue = ctx.packageName

                        MapView(ctx).apply {
                            setMultiTouchControls(true)

                            controller.setZoom(6.0)
                            post {
                                controller.setCenter(GeoPoint(41.8719, 12.5674))
                            }

                            val googleTileSource = object : org.osmdroid.tileprovider.tilesource.XYTileSource(
                                "GoogleMaps",
                                1, 20, 256, ".png",
                                arrayOf("https://mt1.google.com/vt/lyrs=m&hl=it")
                            ) {
                                override fun getTileURLString(pMapTileIndex: Long): String {
                                    return baseUrl + "&x=" + org.osmdroid.util.MapTileIndex.getX(pMapTileIndex) +
                                            "&y=" + org.osmdroid.util.MapTileIndex.getY(pMapTileIndex) +
                                            "&z=" + org.osmdroid.util.MapTileIndex.getZoom(pMapTileIndex)
                                }
                            }
                            setTileSource(googleTileSource)
                            isTilesScaledToDpi = true

                            val iconaCinema = ContextCompat.getDrawable(ctx, R.drawable.ic_cinema)
                            iconaCinema?.setTint(android.graphics.Color.parseColor("#5C0000"))

                            iconaCinema?.setBounds(0, 0,
                                iconaCinema.intrinsicWidth.coerceAtLeast(100),
                                iconaCinema.intrinsicHeight.coerceAtLeast(100)
                            )

                            for (cinema in CinemaMockData.listaCinema) {
                                val marker = Marker(this).apply {
                                    position = GeoPoint(cinema.lat, cinema.lon)
                                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                                    title = cinema.nome
                                    icon = iconaCinema
                                }
                                overlays.add(marker)
                            }

                            invalidate()
                        }
                    }
                )

                ExtendedFloatingActionButton(
                    onClick = {

                        locationPermissionRequest.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp),
                    containerColor = Color(0xFF5C0000),
                    contentColor = Color(0xFFFECE79)
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Cerca")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Cerca sale vicine")
                }
            }
        }
    }
}

data class CinemaMock(val lat: Double, val lon: Double, val nome: String)

object CinemaMockData {
    val listaCinema = listOf(
        CinemaMock(44.4949, 11.3426, "Cinema Astra (Bologna)"),
        CinemaMock(44.4982, 11.3451, "Cinema Lumière - Cineteca (Bologna)"),
        CinemaMock(44.4912, 11.3415, "Cinema Odeon (Bologna)"),
        CinemaMock(44.5916, 11.0504, "Cinema Victoria Multiplex (Modena)"),
        CinemaMock(44.8372, 11.6198, "Apollo Cinepark (Ferrara)"),
        CinemaMock(44.0613, 12.5657, "Multiplex Le Befane (Rimini)"),
        CinemaMock(45.4642, 9.1900, "Anteo Palazzo del Cinema (Milano)"),
        CinemaMock(45.4821, 9.2035, "Anteo CityLife Anteo (Milano)"),
        CinemaMock(45.4627, 9.1772, "Cinema Eliseo (Milano)"),
        CinemaMock(45.4331, 9.1512, "UCI Cinemas Milanofiori (Assago)"),
        CinemaMock(45.6484, 9.2144, "Tris Cinema (Monza)"),
        CinemaMock(45.5391, 10.2203, "Multisala Oz (Brescia)"),
        CinemaMock(41.9028, 12.4964, "Cinema Quattro Fontane (Roma)"),
        CinemaMock(41.9132, 12.4761, "Cinema Adriano (Roma)"),
        CinemaMock(41.8725, 12.4731, "Nuovo Sacher (Roma)"),
        CinemaMock(41.8981, 12.5192, "The Space Cinema - Moderno (Roma)"),
        CinemaMock(41.3934, 13.1114, "Multisala Oxer (Latina)"),
        CinemaMock(40.8518, 14.2681, "Cinema Metropolitan (Napoli)"),
        CinemaMock(40.8422, 14.2501, "Cinema Filangieri (Napoli)"),
        CinemaMock(40.8385, 14.2412, "Cinema Plaza (Napoli)"),
        CinemaMock(40.9144, 14.7932, "Movieplex (Mercogliano)"),
        CinemaMock(40.6781, 14.7654, "Cinema Teatro Augusteo (Salerno)"),
        CinemaMock(43.7696, 11.2558, "Cinema Odeon (Firenze)"),
        CinemaMock(43.7721, 11.2481, "Cinema Spazio Uno (Firenze)"),
        CinemaMock(43.7151, 10.4014, "Odeon Multisala (Pisa)"),
        CinemaMock(43.5512, 10.3124, "Cinema Gran Guardia (Livorno)"),
        CinemaMock(45.0703, 7.6869, "Cinema Massimo (Torino)"),
        CinemaMock(45.0651, 7.6921, "Cinema Classico (Torino)"),
        CinemaMock(45.0691, 7.6698, "Multisala Reposi (Torino)"),
        CinemaMock(45.4412, 8.6154, "VIP Multiplex (Novara)"),
        CinemaMock(45.4382, 12.3351, "Cinema Rossini (Venezia)"),
        CinemaMock(45.4921, 12.2415, "IMG Cinemas Palazzo (Mestre)"),
        CinemaMock(45.4064, 11.8768, "Multiastra (Padova)"),
        CinemaMock(45.4342, 10.9921, "Multisala Rivoli (Verona)"),
        CinemaMock(41.1254, 16.8662, "Multicinema Galleria (Bari)"),
        CinemaMock(41.1091, 16.8841, "Showville (Bari)"),
        CinemaMock(40.3522, 18.1724, "Multisala Massimo (Lecce)"),
        CinemaMock(38.1157, 13.3615, "Cinema Rouge et Noir (Palermo)"),
        CinemaMock(37.5076, 15.0831, "Asciuti Multisala (Catania)"),
        CinemaMock(39.2234, 9.1214, "Cinema Odissea (Cagliari)"),
        CinemaMock(40.5564, 8.3214, "Cinema Miramare (Alghero)")
    )
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(navController = rememberNavController())
}