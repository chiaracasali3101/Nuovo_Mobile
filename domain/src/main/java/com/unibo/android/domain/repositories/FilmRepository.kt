package com.unibo.android.data.repository

import com.unibo.android.data.local.FilmDao
import com.unibo.android.data.entities.FilmEntity
import com.unibo.android.data.remote.FilmApiService

class FilmRepository(
    private val filmDao: FilmDao,
    private val apiService: FilmApiService
) {
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    // Legge i film salvati nel DB locale
    val tuttiIFilm = filmDao.getTuttiIFilm()

    suspend fun sincronizzaFilm(apiKey: String) {
        try {
            // chiamata a TMDB
            val risposta = apiService.getFilmPopolari(apiKey)

            val entities = risposta.results.map { dto ->
                FilmEntity(
                    id = dto.id,
                    titolo = dto.titolo,
                    anno = dto.anno,
                    trama = dto.trama,
                    genere = dto.genere,
                    durata = dto.durata,
                    regista = dto.regista,
                    punteggio = 0,
                    percorsoLocandina = if (dto.percorsoLocandina != null) BASE_IMAGE_URL + dto.percorsoLocandina else "",
                    preferito = false
                )
            }

            // salvataggio nel DB locale
            entities.forEach { film -> filmDao.addWatchlist(movie = film) }

        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getFilmPopolari(apiKey: String) {}

    fun getFilmById(id: Int): FilmEntity? {}
}