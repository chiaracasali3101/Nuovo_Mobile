package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesRicerca(
    private val repository: FilmRepository
) {

    suspend operator fun invoke(query: String, tmdbApiKey: String): List<Film> {
        return try {
            // prova a fare la chiamata di rete
            if (query.isBlank()) {
                repository.getPopularMovies()
            } else {
                repository.cercaFilmOnline(apiKey = tmdbApiKey, query = query)
            }
        } catch (e: Exception) {
            // se c'è un errore (es. chiave API mancante/errata, no internet)
            // stampiamo l'errore nel Logcat per debug e restituiamo una lista vuota
            e.printStackTrace()
            emptyList()
        }
    }

}