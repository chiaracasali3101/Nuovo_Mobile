package com.unibo.android.domain.di

import com.unibo.android.domain.di.UseCasesProvider.useCasesVisto
import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.MovieRepository
import com.unibo.android.domain.usecases.UseCasesRicerca
import com.unibo.android.domain.repositories.FilmRepository
import com.unibo.android.domain.usecases.UseCasesPreferito
import com.unibo.android.domain.usecases.UseCasesVisto
import com.unibo.android.domain.usecases.UseCasesVisualizza
import com.unibo.android.domain.usecases.UseCasesWatchlist

object UseCasesProvider {

    lateinit var useCasesVisto: UseCasesVisto
    private lateinit var movieRepository: MovieRepository

    lateinit var useCasesRicerca: UseCasesRicerca
        private set

    lateinit var useCasesVisualizza: UseCasesVisualizza
        private set

    lateinit var useCasesWatchlist: UseCasesWatchlist
        private set

    lateinit var useCasesPreferito: UseCasesPreferito
        private set

    suspend fun initialize(repository: MovieRepository) {
        this.movieRepository = repository
        this.useCasesRicerca = UseCasesRicerca(repository = movieRepository as FilmRepository)
        this.useCasesVisualizza = UseCasesVisualizza(repository = movieRepository as FilmRepository)
        this.useCasesWatchlist = UseCasesWatchlist(repository = movieRepository as FilmRepository)
        this.useCasesPreferito = UseCasesPreferito(repository = movieRepository as FilmRepository)
        this.useCasesVisto = UseCasesVisto(repository = movieRepository as FilmRepository)
    }
}