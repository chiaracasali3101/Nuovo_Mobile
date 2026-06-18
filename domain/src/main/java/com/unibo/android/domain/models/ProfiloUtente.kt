package com.unibo.android.domain.models

data class UserProfile(
    val nome: String,
    val email: String,
    // Percorso dell'immagine
    val uriImmagine: String? = null,
    // Coordinate per la mappa dei cinema
    val latitudine: Double? = null,
    val longitudine: Double? = null
)