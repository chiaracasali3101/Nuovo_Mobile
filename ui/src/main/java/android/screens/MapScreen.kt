package android.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.unibo.android.ui.components.ReViewBottomBar
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapScreen() {
    // 1. Chiediamo: "Siamo nella Preview finta di Android Studio?"
    val isPreview = LocalInspectionMode.current

    Scaffold(
        bottomBar = { ReViewBottomBar() }
    ) { innerPadding ->

        // 2. Se siamo nella preview, mostriamo un box colorato e INNOCUO
        if (isPreview) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFF210100)), // Il tuo marrone scuro
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
            // 3. Se NON siamo nella preview (quindi siamo sul telefono vero), carichiamo OSM
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                factory = { context ->
                    // Setup essenziale per Osmdroid (evita crash su telefoni veri)
                    Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
                    Configuration.getInstance().userAgentValue = context.packageName

                    MapView(context).apply {
                        setTileSource(TileSourceFactory.MAPNIK)
                        setMultiTouchControls(true)
                        controller.setZoom(15.0)

                        // Coordinate di test (es. centro di Bologna)
                        val posizione = GeoPoint(44.4949, 11.3426)
                        controller.setCenter(posizione)

                        val marker = Marker(this).apply {
                            position = posizione
                            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                            title = "Cinema Astra"
                        }
                        overlays.add(marker)
                    }
                }
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen()
}
