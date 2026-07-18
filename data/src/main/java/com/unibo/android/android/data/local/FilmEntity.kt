package com.unibo.android.android.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.unibo.android.domain.models.Film

@Entity(tableName = "watchlist")
data class FilmEntity(
    @PrimaryKey val id: Int,
    val titolo: String?,
    val anno: String?,
    val trama: String?,
    val genere: String?,
    val durata: String?,
    val regista: String?,
    val punteggio: Double?,
    val percorsoLocandina: String?,
    val preferito: Boolean = false,
    val visto: Boolean = false
) { fun toDomain(): Film {
        return Film(
            id = this.id,
            titolo = this.titolo,
            anno = this.anno ?: "N/D",
            trama = this.trama ?: "Nessuna trama disponibile",
            genere = this.genere ?: "Cinema",
            durata = this.durata ?: "N/D",
            regista = this.regista ?: "N/D",
            punteggio = this.punteggio ?: 0.0,
            percorsoLocandina = this.percorsoLocandina ?: "",
            preferito = this.preferito,
            visto = this.visto
        )
    }
}