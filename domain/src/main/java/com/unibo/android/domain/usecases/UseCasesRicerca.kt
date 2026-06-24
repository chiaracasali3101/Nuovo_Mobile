package com.unibo.android.domain.di

import com.unibo.android.data.entities.FilmEntity
import com.unibo.android.domain.repositories.FilmRepository
import com.unibo.android.domain.repositories.MovieRepository

class UseCasesRicerca(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(query: String): List<FilmEntity> {
        return repository.getFilmsByQuery(query)
    }

    fun execute(query: String) {}

}

