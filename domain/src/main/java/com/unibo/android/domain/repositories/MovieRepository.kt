package com.unibo.android.domain.repositories

import com.unibo.android.domain.models.FilmType
import kotlinx.coroutines.flow.StateFlow

interface MovieRepository {
    //chiara
    val movieList: StateFlow<List<FilmType>>
    fun startFetchMovieList()

    //laura
    suspend fun getPopularMovies(): List<MovieDto>

    suspend fun getTopRatedMovies(): List<MovieDto>
}
