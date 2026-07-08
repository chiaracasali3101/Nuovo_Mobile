package com.unibo.android.android.data.repositories

import com.unibo.android.android.data.local.FilmEntity
import com.unibo.android.android.data.local.FilmDao
import com.unibo.android.data.remote.FilmApiService
import com.unibo.android.domain.repositories.FilmRepository
import com.unibo.android.domain.models.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepositoryImpl(
    private val filmDao: FilmDao,
    private val apiService: FilmApiService
) : FilmRepository {

    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    override fun getTuttiIFilm(): Flow<List<Film>> {
        return filmDao.getTuttiIFilm().map { listaEntity ->
            listaEntity.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun getFilmsByQuery(query: String): List<Film> {
        return filmDao.getFilmsByQuery(query).map { it.toDomain() }
    }

    override suspend fun getFilmById(id: Int): Film? {
        return filmDao.getFilmById(id)?.toDomain()
    }

    override suspend fun cercaFilmOnline(apiKey: String, query: String): List<Film> {
        return try {
            val risposta = apiService.cercaFilm(apiKey = apiKey, query = query)
            risposta.results.map { dto ->
                // Creiamo l'entità locale temporanea
                val entity = FilmEntity(
                    id = dto.id,
                    titolo = dto.titolo,
                    anno = "N/D",
                    trama = dto.trama ?: "Nessuna trama disponibile",
                    genere = "Cinema",
                    durata = "N/D",
                    regista = "N/D",
                    punteggio = 5.0,
                    percorsoLocandina = dto.percorsoLocandina ?: "",
                    preferito = false
                )
                // La convertiamo usando la funzione toDomain corretta
                entity.toDomain()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun sincronizzaFilm(apiKey: String) {
        try {
            val risposta = apiService.getFilmPopolari(apiKey)

            risposta.results.forEach { dto ->
                filmDao.addWatchlist(
                    FilmEntity(
                        id = dto.id,
                        titolo = dto.titolo,
                        anno = "N/D",
                        trama = dto.trama ?: "Nessuna trama disponibile",
                        genere = "Cinema",
                        durata = "N/D",
                        regista = "N/D",
                        punteggio = 5.0,
                        percorsoLocandina = dto.percorsoLocandina ?: "",
                        preferito = false
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun FilmEntity.toDomain(): Film {
    return Film(
        titolo = this.titolo,
        anno = this.anno,
        trama = this.trama,
        genere = this.genere,
        durata = this.durata,
        regista = this.regista,
        punteggio = this.punteggio,
        percorsoLocandina = this.percorsoLocandina,
        preferito = this.preferito
    )
}