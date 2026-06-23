package com.unibo.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unibo.android.ui.R
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
) {
    val sfondoMarrone = Color(0xFF210100)
    val coloreCrema = Color(0xFFFECE79)
    val coloreOro = Color(0xFFE6A341)
    val coloreRossoBottoni = Color(0xFF8C0902)

    // --- MODIFICA EFFETTUATA QUI ---
    // Colore rosso scuro pieno, senza trasparenza, coordinato con il profilo
    val contenitoreInput = Color(0xFF5C0805)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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

        Spacer(modifier = Modifier.height(60.dp))

        // --- CAMPO EMAIL ---
        Text(
            text = "EMAIL",
            color = coloreCrema,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start).padding(start = 10.dp, bottom = 6.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Inserisci la tua email", color = coloreCrema.copy(alpha = 0.4f)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = contenitoreInput,
                unfocusedContainerColor = contenitoreInput,
                focusedTextColor = coloreCrema,
                unfocusedTextColor = coloreCrema,
                cursorColor = coloreOro,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --- CAMPO PASSWORD ---
        Text(
            text = "PASSWORD",
            color = coloreCrema,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start).padding(start = 10.dp, bottom = 6.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Inserisci la password", color = coloreCrema.copy(alpha = 0.4f)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = if (passwordVisibile) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val immagineIcona = if (passwordVisibile) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                IconButton(onClick = { passwordVisibile = !passwordVisibile }) {
                    Icon(painter = painterResource(id = immagineIcona), contentDescription = null, tint = coloreCrema.copy(alpha = 0.6f))
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = contenitoreInput,
                unfocusedContainerColor = contenitoreInput,
                focusedTextColor = coloreCrema,
                unfocusedTextColor = coloreCrema,
                cursorColor = coloreOro,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        // --- BOTTONE ACCEDI ---
        Button(
            onClick = { onLoginSuccess() },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = coloreRossoBottoni),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("ACCEDI", color = coloreCrema, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(25.dp))

        // --- LINK REGISTRAZIONE ---
        Row {
            Text("Nuovo su reView? ", color = coloreCrema.copy(alpha = 0.6f), fontSize = 14.sp)
            Text(
                text = "Registrati ora",
                color = coloreOro,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNavigateToRegister() }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun LoginPreview() {
    LoginScreen()
}