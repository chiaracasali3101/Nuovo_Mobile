package com.unibo.android.domain.di

import com.unibo.android.domain.repositories.MovieRepository
import com.unibo.android.domain.usecases.UseCasesRicerca
import com.unibo.android.domain.repositories.FilmRepository
import com.unibo.android.domain.usecases.UseCasesVisualizza
import com.unibo.android.domain.usecases.UseCasesWatchlist

object UseCasesProvider {
    private lateinit var movieRepository: MovieRepository

    lateinit var useCasesRicerca: UseCasesRicerca
        private set

    lateinit var useCasesVisualizza: UseCasesVisualizza
        private set

    lateinit var useCasesWatchlist: UseCasesWatchlist
        private set

    fun initialize(repository: MovieRepository) {
        this.movieRepository = repository
        this.useCasesRicerca = UseCasesRicerca(repository = movieRepository as FilmRepository)
        this.useCasesVisualizza = UseCasesVisualizza(repository = movieRepository as FilmRepository)
        this.useCasesWatchlist = UseCasesWatchlist(repository = movieRepository as FilmRepository)
    }
}