package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesVisualizza(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(id: Int): Film? {
        return repository.getFilmById(id)
    }
}