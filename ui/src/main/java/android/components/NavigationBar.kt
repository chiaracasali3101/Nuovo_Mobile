package android.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.sp

data class NavItem(val label: String, val icon: ImageVector)

@Composable
fun ReViewBottomBar(
    selectedItem: Int = 0, // Aggiungendo "= 0" diventa facoltativo
    onItemSelected: (Int) -> Unit = {} // Aggiungendo "= {}" diventa facoltativo
) {
    val navItems = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Cerca", Icons.Default.Search),
        NavItem("Classifica", Icons.Default.Star),
        NavItem("Profilo", Icons.Default.Person),
        NavItem("Login", Icons.Default.Lock)
    )

    val sfondoNavBar = Color(0xFF5A0000)
    val coloreOro = Color(0xFFFECE79)
    val coloreInattivo = Color.White.copy(alpha = 0.6f)

    NavigationBar(
        containerColor = sfondoNavBar,
        contentColor = coloreOro,
        modifier = Modifier.height(85.dp)
    ) {
        navItems.forEachIndexed { index, item ->
            val isSearch = item.label == "Cerca"

            NavigationBarItem(
                selected = selectedItem == index, // Parametro 'selected'
                onClick = { onItemSelected(index) }, // Parametro 'onClick'
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.label)
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp
                    )
                },
                alwaysShowLabel = true,
                modifier = Modifier.padding(top = 8.dp),
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = sfondoNavBar,
                    indicatorColor = coloreOro,
                    selectedTextColor = coloreOro,
                    unselectedIconColor = coloreInattivo,
                    unselectedTextColor = coloreInattivo
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReViewBottomBarPreview() {
    MaterialTheme {
        ReViewBottomBar(
            selectedItem = 0,
            onItemSelected = { }
        )
    }
}
