package com.unibo.android.domain.repositories

import com.unibo.android.domain.models.Film
import kotlinx.coroutines.flow.StateFlow

interface MovieRepository {
    //chiara
    val movieList: StateFlow<List<Film>>
    fun startFetchMovieList()

    //laura
    suspend fun getPopularMovies(): List<Film>

    suspend fun getTopRatedMovies(): List<Film>
    suspend fun getFilmsByQuery(query: String): List<Film>

}
