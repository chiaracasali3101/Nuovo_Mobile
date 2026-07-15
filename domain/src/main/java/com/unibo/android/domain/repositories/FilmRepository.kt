package com.unibo.android.domain.repositories

import com.unibo.android.domain.models.Film
import kotlinx.coroutines.flow.Flow


interface FilmRepository {

    fun getTuttiIFilm(): Flow<List<com.unibo.android.domain.models.Film>>

    suspend fun getFilmsByQuery(query: String): List<com.unibo.android.domain.models.Film>

    suspend fun getFilmById(id: Int): com.unibo.android.domain.models.Film?

    suspend fun cercaFilmOnline(apiKey: String, query: String): List<com.unibo.android.domain.models.Film>

    suspend fun sincronizzaFilm(apiKey: String)

    suspend fun addWatchlist(film: com.unibo.android.domain.models.Film)
    suspend fun getPopularMovies(): List<com.unibo.android.domain.models.Film>
}