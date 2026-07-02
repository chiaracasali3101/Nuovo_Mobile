package com.unibo.android.domain

class Film (
    val id: Int,
    val titolo: String,
    val anno: String,
    val trama: String,
    val genere: String,
    val durata: String,
    val regista: String,
    val punteggio: Double,
    val percorsoLocandina: String,
    val preferito: Boolean = false
)