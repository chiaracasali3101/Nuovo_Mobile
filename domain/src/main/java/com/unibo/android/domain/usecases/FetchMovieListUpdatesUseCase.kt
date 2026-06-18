package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.FilmType
import com.unibo.android.domain.repositories.MovieRepository

// Interfaccia per il caso d'uso
interface GetMovieListUseCase {
    suspend fun execute(): List<FilmType>
}

class GetMovieListUseCaseImpl(
    private val movieRepository: MovieRepository
) : GetMovieListUseCase {

    override suspend fun execute(): List<FilmType> {
        // Usiamo .value perché movieList è una StateFlow
        return movieRepository.movieList.value
    }
}