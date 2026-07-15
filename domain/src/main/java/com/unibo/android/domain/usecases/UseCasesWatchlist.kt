package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesWatchlist(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(film: Film) {
        repository.addWatchlist(film)
    }
}