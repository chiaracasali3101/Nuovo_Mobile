package android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.domain.models.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TMDB_API_KEY = "f68e046df68555567f96d4cdfcc3ffdf"

class RicercaViewModel : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _listaFilm = MutableStateFlow<List<Film>>(emptyList())
    val listaFilm: StateFlow<List<Film>> = _listaFilm.asStateFlow()

    fun aggiornaQuery(nuovaQuery: String) {
        _query.value = nuovaQuery
        eseguiRicerca(nuovaQuery)
    }

    private fun eseguiRicerca(testo: String) {
        if (testo.isBlank()) {
            _listaFilm.value = emptyList()
            return
        }

        viewModelScope.launch {
            // Spostiamo la chiamata all'Use Case di ricerca dentro il ViewModel (fuori dall'Activity!)
            val risultati = withContext(Dispatchers.IO) {
                UseCasesProvider.useCasesRicerca(testo, tmdbApiKey = TMDB_API_KEY)
            }
            _listaFilm.value = risultati
        }
    }
}