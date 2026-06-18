package com.unibo.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    val sfondoMarrone = Color(0xFF210100)
    val coloreCrema = Color(0xFFFECE79)
    val coloreOro = Color(0xFFE6A341)
    val coloreRossoBottoni = Color(0xFF8C0902)
    val contenitoreInput = Color(0xFF5C0805)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confermaPassword by remember { mutableStateOf("") }
    var passwordVisibile by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(sfondoMarrone)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- LOGO BRAND (reView) ---
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "re", color = coloreCrema, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text(text = "View", color = Color.Red, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        }

        Text(
            text = "I tuoi film, le tue recensioni.",
            color = coloreCrema.copy(alpha = 0.6f),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // --- TITOLO DELLA PAGINA ---
        Text(text = "Crea un account", color = coloreCrema, fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        // --- CAMPO EMAIL ---
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email", color = coloreCrema.copy(alpha = 0.4f)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = contenitoreInput, unfocusedContainerColor = contenitoreInput,
                focusedTextColor = coloreCrema, unfocusedTextColor = coloreCrema,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(15.dp))

        // --- CAMPO PASSWORD ---
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password", color = coloreCrema.copy(alpha = 0.4f)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = if (passwordVisibile) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = contenitoreInput, unfocusedContainerColor = contenitoreInput,
                focusedTextColor = coloreCrema, unfocusedTextColor = coloreCrema,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(15.dp))

        // --- CAMPO CONFERMA PASSWORD ---
        TextField(
            value = confermaPassword,
            onValueChange = { confermaPassword = it },
            placeholder = { Text("Conferma Password", color = coloreCrema.copy(alpha = 0.4f)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = if (passwordVisibile) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = contenitoreInput, unfocusedContainerColor = contenitoreInput,
                focusedTextColor = coloreCrema, unfocusedTextColor = coloreCrema,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        // --- BOTTONE REGISTRATI ---
        Button(
            onClick = { onRegisterSuccess() },
            modifier = Modifier.fillMaxWidth().height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = coloreRossoBottoni),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("REGISTRATI", color = coloreCrema, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(25.dp))

        // --- LINK TORNA AL LOGIN ---
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Hai già un account? ", color = coloreCrema.copy(alpha = 0.6f), fontSize = 14.sp)
            Text(
                text = "Accedi", color = coloreOro, fontSize = 14.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNavigateToLogin() }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun RegisterPreview() {
    RegisterScreen()
}