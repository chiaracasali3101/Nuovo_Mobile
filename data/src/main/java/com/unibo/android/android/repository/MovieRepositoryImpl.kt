package com.unibo.android.corsolp2526.data.repository

import com.unibo.android.corsolp2526.data.api.TmdbApi
import com.unibo.android.domain.repositories.MovieRepository
import com.unibo.android.data.entities.FilmEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieRepositoryImpl(
    private val api: TmdbApi,
    private val apiKey: String
) : MovieRepository {

    //private val _movieList = MutableStateFlow<List<Any>>(emptyList())
    //override val movieList: StateFlow<List<Any>> = _movieList.asStateFlow()
    private val _movieList = MutableStateFlow<List<FilmEntity>>(emptyList())
    override val movieList: StateFlow<List<FilmEntity>> = _movieList.asStateFlow()

    override fun startFetchMovieList() {
    }

    override suspend fun getPopularMovies(): List<FilmEntity> {
        return try {
            val response = api.getPopularMovies(apiKey)
            response.results.map { dto ->
                FilmEntity(
                    id = dto.id,
                    titolo = dto.title ?: "Nessun titolo",
                    anno = "N/D",
                    trama = dto.overview ?: "Nessuna trama disponibile",
                    genere = "Cinema",
                    durata = "N/D",
                    regista = "N/D",
                    punteggio = 5.0,
                    percorsoLocandina = dto.poster_path ?: "",
                    preferito = false
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getTopRatedMovies(): List<FilmEntity> {
        return try {
            val response = api.getTopRatedMovies(apiKey)
            response.results.map { dto ->
                FilmEntity(
                    id = dto.id,
                    titolo = dto.title ?: "Nessun titolo",
                    anno = "N/D",
                    trama = dto.overview ?: "Nessuna trama disponibile",
                    genere = "Cinema",
                    durata = "N/D",
                    regista = "N/D",
                    punteggio = 5.0,
                    percorsoLocandina = dto.poster_path ?: "",
                    preferito = false
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getFilmsByQuery(query: String): List<FilmEntity> {
        TODO("Not yet implemented")
    }

}