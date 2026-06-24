package com.unibo.android.domain.repositories

import com.unibo.android.corsolp2526.data.model.MovieDto
import com.unibo.android.data.entities.FilmEntity
import com.unibo.android.domain.models.FilmType
import kotlinx.coroutines.flow.StateFlow

interface MovieRepository {
    //chiara
    val movieList: StateFlow<List<FilmEntity>>
    fun startFetchMovieList()

    //laura
    suspend fun getPopularMovies(): List<FilmEntity>

    suspend fun getTopRatedMovies(): List<FilmEntity>
    suspend fun getFilmsByQuery(query: String): List<FilmEntity>

}
