package com.unibo.android.domain

class Film (
    val titolo: String,
    val anno: String,
    val trama: String,
    val genere: String,
    val durata: String,
    val regista: String,
    val punteggio: Double,
    val preferito: Boolean = false
)