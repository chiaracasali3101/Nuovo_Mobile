package com.unibo.android.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist")
data class FilmEntity(
    @PrimaryKey val id: Int,
    val titolo: String,
    val anno: String,
    val trama: String,
    val genere: String,
    val durata: String,
    val regista: String,
    val punteggio: Int,
    val percorsoLocandina: String,
    val preferito: Boolean = false
)