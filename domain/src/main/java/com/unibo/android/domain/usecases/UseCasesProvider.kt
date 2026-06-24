/*package com.unibo.android.domain.di

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
*/

package com.unibo.android.domain.di

import com.unibo.android.domain.repositories.MovieRepository
import com.unibo.android.domain.di.UseCasesRicerca
import com.unibo.android.domain.usecases.UseCasesVisualizza

object UseCasesProvider {
    private lateinit var movieRepository: MovieRepository // Prima era FilmRepository

    lateinit var useCasesRicerca: UseCasesRicerca
        private set

    lateinit var useCasesVisualizza: UseCasesVisualizza
        private set

    // Cambia il parametro qui in MovieRepository
    fun initialize(repository: MovieRepository) {
        this.movieRepository = repository
        this.useCasesRicerca = UseCasesRicerca(repository = movieRepository)
        this.useCasesVisualizza = UseCasesVisualizza(repository = movieRepository)
    }
}
