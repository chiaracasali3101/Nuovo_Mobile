package com.unibo.android.android.data.repositories

import com.unibo.android.android.data.local.FilmEntity
import com.unibo.android.android.data.local.FilmDao
import com.unibo.android.data.remote.FilmApiService
import com.unibo.android.domain.repositories.FilmRepository
import com.unibo.android.domain.repositories.MovieRepository
import com.unibo.android.domain.models.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class FilmRepositoryImpl(
    private val filmDao: FilmDao,
    private val apiService: FilmApiService,
    private val apiKey: String
) : FilmRepository, MovieRepository {

    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    // --- MovieRepository ---
    private val _movieList = MutableStateFlow<List<Film>>(emptyList())
    override val movieList: StateFlow<List<Film>> = _movieList

    override fun startFetchMovieList() {
        CoroutineScope(Dispatchers.IO).launch {
            _movieList.value = getPopularMovies()
        }
    }

    override suspend fun getPopularMovies(): List<Film> {
        return apiService.getFilmPopolari(apiKey = apiKey).results.map { dto ->
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
                preferito = false,
                visto = false
            ).toDomain()
        }
    }

    override suspend fun getTopRatedMovies(): List<Film> {
        return getPopularMovies()
    }

    // --- FilmRepository ---
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
                    preferito = false,
                    visto = false
                )
                entity.toDomain()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            android.util.Log.e("CercaFilmOnline", "Errore ricerca: ${e.message}", e)
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
                        preferito = false,
                        visto = false
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun addWatchlist(film: Film) {
        filmDao.addWatchlist(
            FilmEntity(
                id = film.id,
                titolo = film.titolo,
                anno = film.anno,
                trama = film.trama,
                genere = film.genere,
                durata = film.durata,
                regista = film.regista,
                punteggio = film.punteggio,
                percorsoLocandina = film.percorsoLocandina,
                preferito = false,
                visto = film.visto
            )
        )
    }

    override suspend fun preferito(film: Film, nuovoValore: Boolean) {
        val esisteGia = filmDao.getFilmById(film.id) != null
        val entity = FilmEntity(
            id = film.id,
            titolo = film.titolo,
            anno = film.anno,
            trama = film.trama,
            genere = film.genere,
            durata = film.durata,
            regista = film.regista,
            punteggio = film.punteggio,
            percorsoLocandina = film.percorsoLocandina,
            preferito = nuovoValore,
            visto = film.visto
        )
        if (esisteGia) {
            filmDao.updateFilm(entity)
        } else {
            filmDao.addWatchlist(entity)
        }
    }

    override suspend fun impostaVisto(
        film: Film,
        nuovoValore: Boolean
    ) {
        val esisteGia = filmDao.getFilmById(film.id) != null
        val entity = film.toEntity().copy(visto = nuovoValore)

        if (esisteGia) {
            filmDao.updateFilm(entity)
        } else {
            filmDao.addWatchlist(entity)
        }
    }
}

fun Film.toEntity(): FilmEntity {
    return FilmEntity(
        id = this.id,
        titolo = this.titolo,
        anno = this.anno,
        trama = this.trama,
        genere = this.genere,
        durata = this.durata,
        regista = this.regista,
        punteggio = this.punteggio,
        percorsoLocandina = this.percorsoLocandina,
        preferito = this.preferito == true,
        visto = this.visto
    )
}