package com.unibo.android.domain.models

data class Film(
    var id: Int = 0,
    var titolo: String? = null,
    var anno: String? = null,
    var trama: String? = null,
    var genere: String? = null,
    var durata: String? = null,
    var regista: String? = null,
    var punteggio: Double? = 0.0,
    var percorsoLocandina: String? = null,
    var preferito: Boolean? = false
)