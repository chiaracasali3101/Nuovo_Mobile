package com.unibo.android.ui.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme

data class NavItem(val label: String, val icon: ImageVector)

@Composable
fun ReViewBottomBar() {
    val navItems = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Cerca", Icons.Default.Search),
        NavItem("Classifica", Icons.Default.Star),
        NavItem("Profilo", Icons.Default.Person),
        NavItem("Login", Icons.Default.Lock)
    )

    // --- IL COLORE ESATTO DEL TUO BASTONCINO "FILM VISTI" ---
    val sfondoNavBar = Color(0xFF5A0000)
    val coloreOro = Color(0xFFFECE79)
    val coloreInattivo = Color.White.copy(alpha = 0.6f)

    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = sfondoNavBar,
        contentColor = coloreOro,
    ) {
        navItems.forEachIndexed { index, item ->
            val isSearch = item.label == "Cerca"

            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = if (isSearch) null else {
                    { Text(text = item.label) }
                },
                alwaysShowLabel = !isSearch,
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
        ReViewBottomBar()
    }
}