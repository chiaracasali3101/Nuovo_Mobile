package com.unibo.android.domain.repositories

import com.unibo.android.data.entities.FilmEntity
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getTuttiIFilm(): Flow<List<FilmEntity>>
    suspend fun getFilmsByQuery(query: String): List<FilmEntity>
    suspend fun getFilmById(id: Int): FilmEntity?
    suspend fun cercaFilmOnline(apiKey: String, query: String): List<FilmEntity>
    suspend fun sincronizzaFilm(apiKey: String)
}