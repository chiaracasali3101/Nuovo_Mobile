package com.unibo.android.domain.di

import com.unibo.android.domain.models.FilmEntity
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesRicerca(
    private val repository: FilmRepository
) {

    suspend operator fun invoke(query: String): List<FilmEntity> {
        return repository.getFilmsByQuery(query)
    }

    fun execute(query: String) {}

}