package com.unibo.android.android.data.repositories

import com.unibo.android.data.local.FilmDao
import com.unibo.android.data.remote.FilmApiService
import com.unibo.android.domain.repositories.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.unibo.android.domain.models.Film
import kotlinx.coroutines.flow.flow


class FilmRepositoryImpl(
    private val filmDao: FilmDao,
    private val apiService: FilmApiService
) : FilmRepository {

    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    override suspend fun getTuttiIFilm(): Flow<List<Film>> {
       /* return filmDao.getTuttiIFilm().map { listaDeiDataFilm ->
            listaDeiDataFilm.map { dataFilm ->
                Film(
                    id = dataFilm.id,
                    titolo = dataFilm.titolo,
                    anno = dataFilm.anno,
                    trama = dataFilm.trama,
                    genere = dataFilm.genere,
                    durata = dataFilm.durata,
                    regista = dataFilm.regista,
                    punteggio = dataFilm.punteggio,
                    percorsoLocandina = dataFilm.percorsoLocandina,
                    preferito = dataFilm.preferito
                )
            }
        }*/
        return flow(emptyList<>())
    }

    override suspend fun getFilmsByQuery(query: String): List<Film> {
        return filmDao.getFilmsByQuery(query).map { dataFilm ->
            Film(
                id = dataFilm.id,
                titolo = dataFilm.titolo,
                anno = dataFilm.anno,
                trama = dataFilm.trama,
                genere = dataFilm.genere,
                durata = dataFilm.durata,
                regista = dataFilm.regista,
                punteggio = dataFilm.punteggio,
                percorsoLocandina = dataFilm.percorsoLocandina,
                preferito = dataFilm.preferito
            )
        }
    }

    override suspend fun getFilmById(id: Int): Film? {
        val dataFilm = filmDao.getFilmById(id) ?: return null
        return Film(
            id = dataFilm.id,
            titolo = dataFilm.titolo,
            anno = dataFilm.anno,
            trama = dataFilm.trama,
            genere = dataFilm.genere,
            durata = dataFilm.durata,
            regista = dataFilm.regista,
            punteggio = dataFilm.punteggio,
            percorsoLocandina = dataFilm.percorsoLocandina,
            preferito = dataFilm.preferito
        )
    }

    override suspend fun cercaFilmOnline(apiKey: String, query: String): List<Film> {
        return try {
            val risposta = apiService.cercaFilm(apiKey = apiKey, query = query)
            risposta.results.map { dto ->
                Film(
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
                    movie = Film(
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