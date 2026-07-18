package com.unibo.android.data.remote

import com.google.gson.annotations.SerializedName //gson = libreria per interrogare il sito, fa parsing da json a kotlin
data class TmdbResponse(
    @SerializedName("results") val results: List<TmdbMovieDto>
)

data class TmdbMovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val titolo: String,
    @SerializedName("release_date") val anno: String?,
    @SerializedName("overview") val trama: String,
    @SerializedName("vote_average") val punteggio: Double?,
    val genere: String = "",
    val durata: String = "",
    val regista: String = "",
    @SerializedName("poster_path") val percorsoLocandina: String?
)