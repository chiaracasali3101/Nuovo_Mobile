package android.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.domain.models.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DettaglioViewModel : ViewModel() {

    var filmSelezionato by mutableStateOf<Film?>(null)
        private set

    fun selezionaFilm(film: Film) {
        filmSelezionato = film
    }

    fun deselezionaFilm() {
        filmSelezionato = null
    }

    fun aggiungiAllaWatchlist(film: Film) {
        viewModelScope.launch(Dispatchers.IO) {
            UseCasesProvider.useCasesWatchlist(film)
        }
    }

    fun preferito(film: Film, preferito: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            UseCasesProvider.useCasesPreferito(film, preferito)
        }
    }

    fun impostaVisto(film: Film, visto: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            UseCasesProvider.useCasesVisto(film, visto)
        }
    }
}