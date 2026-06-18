package com.unibo.android.domain.di

import com.unibo.android.domain.repositories.FilmRepository
import com.unibo.android.domain.usecases.UseCasesVisualizza

object UseCasesProvider {
    private lateinit var filmRepository: FilmRepository

    lateinit var useCasesRicerca: UseCasesRicerca
        private set

    lateinit var useCasesVisualizza: UseCasesVisualizza
        private set

    fun initialize(repository: FilmRepository) {
        this.filmRepository = repository

        this.useCasesRicerca = UseCasesRicerca(repository = filmRepository)
        this.useCasesVisualizza = UseCasesVisualizza(repository = filmRepository)
    }
}