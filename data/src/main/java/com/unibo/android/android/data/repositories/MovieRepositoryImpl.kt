package com.unibo.android.data.repositories

import com.unibo.android.domain.models.FilmType
import com.unibo.android.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieRepositoryImpl : MovieRepository {

    // Aggiunto il terzo parametro (stringa vuota) per evitare l'errore "No value passed for descrizione"
    private val localMovies = listOf(
        FilmType("Inception", "8.5", ""),
        FilmType("Interstellar", "8.5", ""),
        FilmType("Pulp Fiction", "9.0", "")
    )

    private val _movieList = MutableStateFlow<List<FilmType>>(emptyList())
    override val movieList: StateFlow<List<FilmType>> = _movieList.asStateFlow()

    override fun startFetchMovieList() {
        _movieList.value = localMovies
    }
}