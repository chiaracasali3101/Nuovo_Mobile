package com.unibo.android.domain.usecases

import com.unibo.android.data.entities.FilmEntity
import com.unibo.android.data.repository.FilmRepository
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesVisualizza(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(id: Int): FilmEntity? {
        return repository.getFilmById(id)
    }
}