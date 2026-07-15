package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesRicerca(
    private val repository: FilmRepository
) {

    suspend operator fun invoke(query: String, tmdbApiKey: String): List<Film> {
        return if (query.isBlank()) {
            repository.getPopularMovies()
        } else {
            repository.cercaFilmOnline(apiKey = tmdbApiKey, query = query)
        }
    }

}