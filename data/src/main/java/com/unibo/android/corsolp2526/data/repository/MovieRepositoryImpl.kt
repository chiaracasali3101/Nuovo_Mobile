package com.unibo.android.corsolp2526.data.repository

import com.unibo.android.corsolp2526.data.api.TmdbApi // O il nome esatto della tua API
import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.StateFlow

class MovieRepositoryImpl(
    private val api: Any, // Sostituiremo 'Any' col tipo corretto della tua API se serve
    private val apiKey: String
) : MovieRepository {

    // Qui dentro Android Studio ti chiederà di implementare
    // le funzioni che hai definito nel tuo MovieRepository (es. getMovies)
    override val movieList: StateFlow<List<Film>>
        get() = TODO("Not yet implemented")

    override fun startFetchMovieList() {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovies(): List<Film> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(): List<Film> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilmsByQuery(query: String): List<Film> {
        TODO("Not yet implemented")
    }
}
