package com.unibo.android.domain.di

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.MovieRepository

class UseCasesRicerca(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(query: String): List<Film> {
        return repository.getFilmsByQuery(query)
    }

    fun execute(query: String) {}

}

