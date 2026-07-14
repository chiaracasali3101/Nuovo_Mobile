/*package com.unibo.android.domain.di

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesRicerca(
    private val repository: FilmRepository
) {

    suspend operator fun invoke(query: String, apiKey: String): List<Film> {
        if (query.isBlank()) return emptyList()
        return repository.cercaFilmOnline(apiKey = apiKey, query = query)
    }
}
 */
package com.unibo.android.domain.di

import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository

class UseCasesRicerca(
    private val repository: FilmRepository
) {

    suspend operator fun invoke(query: String, apiKey: String): List<Film> {
        android.util.Log.d("UseCasesRicercaDebug", "Chiamato con query=$query apiKey=$apiKey")
        if (query.isBlank()) return emptyList()
        return repository.cercaFilmOnline(apiKey = apiKey, query = query)
    }

}