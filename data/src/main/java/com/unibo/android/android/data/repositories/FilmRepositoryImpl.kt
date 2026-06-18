package com.unibo.android.android.data.repositories 

class FilmRepositoryImpl(
    private val filmDao: FilmDao,
    private val apiService: FilmApiService
) : FilmRepository {

    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    override suspend fun getTuttiIFilm(): Flow<List<DomainFilmEntity>> {
        return filmDao.getTuttiIFilm().map { listaDeiDataFilm ->
            listaDeiDataFilm.map { dataFilm ->
                DomainFilmEntity(
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
    }

    override suspend fun getFilmsByQuery(query: String): List<DomainFilmEntity> {
        return filmDao.getFilmsByQuery(query).map { dataFilm ->
            DomainFilmEntity(
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

    override suspend fun getFilmById(id: Int): DomainFilmEntity? {
        val dataFilm = filmDao.getFilmById(id) ?: return null
        return DomainFilmEntity(
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

    override suspend fun cercaFilmOnline(apiKey: String, query: String): List<DomainFilmEntity> {
        return try {
            val risposta = apiService.cercaFilm(apiKey = apiKey, query = query)
            risposta.results.map { dto ->
                DomainFilmEntity(
                    id = dto.id,
                    titolo = dto.titolo,
                    anno = "N/D",
                    trama = dto.trama ?: "Nessuna trama disponibile",
                    genere = "Cinema",
                    durata = "N/D",
                    regista = "N/D",
                    punteggio = "5",
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
                    movie = DataFilmEntity(
                        id = dto.id,
                        titolo = dto.titolo,
                        anno = "N/D",
                        trama = dto.trama ?: "Nessuna trama disponibile",
                        genere = "Cinema",
                        durata = "N/D",
                        regista = "N/D",
                        punteggio = "5",
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