package com.unibo.android.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeFilmRepository {
    private val filmFinti = listOf(
        FilmMock("1", "Inception", "Christopher Nolan", "Fantascienza"),
        FilmMock("2", "Interstellar", "Christopher Nolan", "Fantascienza"),
        FilmMock("3", "Il Cavaliere Oscuro", "Christopher Nolan", "Azione"),
        FilmMock("4", "Pulp Fiction", "Quentin Tarantino", "Thriller")
    )

    fun cercaFilmPerTitolo(query: String): List<FilmMock> {
        if (query.isBlank()) return emptyList()
        return filmFinti.filter { it.titolo.contains(query, ignoreCase = true) }
    }
}

//struttura per i test
data class FilmMock(
    val id: String,
    val titolo: String,
    val regista: String,
    val genere: String
)

class UseCasesRicercaTest {

    private val repository = FakeFilmRepository()

    @Test
    fun `ricerca film con titolo esistente restituisce i risultati corretti`() {
        val query = "Inception"

        val risultato = repository.cercaFilmPerTitolo(query)

        assertEquals(1, risultato.size)
        assertEquals("Inception", risultato.first().titolo)
        assertEquals("Christopher Nolan", risultato.first().regista)
    }

    @Test
    fun `ricerca film parziale restituisce tutti i film corrispondenti`() {

        val query = "Ince"

        val risultato = repository.cercaFilmPerTitolo(query)

        assertEquals(1, risultato.size)
        assertTrue(risultato.any { it.titolo == "Inception" })
    }

    @Test
    fun `ricerca film con stringa vuota restituisce lista vuota`() {
        val query = "   "

        val risultato = repository.cercaFilmPerTitolo(query)

        assertTrue(risultato.isEmpty())
    }

    @Test
    fun `ricerca film con titolo inesistente restituisce lista vuota`() {
        val query = "Avatar"

        val risultato = repository.cercaFilmPerTitolo(query)
        
        assertEquals(0, risultato.size)
        assertTrue(risultato.isEmpty())
    }
}