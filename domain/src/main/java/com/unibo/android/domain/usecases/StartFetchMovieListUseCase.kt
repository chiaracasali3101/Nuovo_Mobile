package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.MovieRepository

interface StartFetchMovieListUseCase {
    operator fun invoke()
}

class StartFetchMovieListUseCaseImpl(
    private val movieRepository: MovieRepository
) : StartFetchMovieListUseCase {
    override fun invoke() {
        movieRepository.startFetchMovieList()
    }
}