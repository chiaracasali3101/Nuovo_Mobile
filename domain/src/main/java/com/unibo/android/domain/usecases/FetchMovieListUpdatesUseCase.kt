package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.MovieRepository

// Interfaccia per il caso d'uso
interface GetMovieListUseCase {
    suspend fun execute(): List<Film>
}

class GetMovieListUseCaseImpl(
    private val movieRepository: MovieRepository
) : GetMovieListUseCase {

    override suspend fun execute(): List<Film> {
        return movieRepository.movieList.value
    }
}