package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesVisto(private val repository: FilmRepository) {

    suspend operator fun invoke(film: Film, visto: Boolean) {
        repository.impostaVisto(film, visto)
    }

    fun impostaVisto(it: com.unibo.android.domain.models.Film, nuovoValore: Boolean) {}
}