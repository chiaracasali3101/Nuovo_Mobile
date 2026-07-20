package android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(private val repository: FilmRepository) : ViewModel() {
    private val tuttiIFilm = repository.getTuttiIFilm()

    // Film Visti: prende solo i film in cui la spunta "visto" è true
    val filmVisti: StateFlow<List<Film>> = tuttiIFilm
        .map { lista -> lista.filter { it.visto } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Watchlist: prende i film aggiunti al database che NON sono ancora stati visti
    val watchlist: StateFlow<List<Film>> = tuttiIFilm
        .map { lista -> lista.filter { !it.visto } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // MODIFICA 1: Ora peschiamo i dati iniziali dalla nostra finta memoria condivisa
    private val _nomeUtente = MutableStateFlow(SessionManager.nomeUtenteAttuale)
    val nomeUtente: StateFlow<String> = _nomeUtente

    private val _emailUtente = MutableStateFlow(SessionManager.emailUtenteAttuale)
    val emailUtente: StateFlow<String> = _emailUtente

    // per aggiornare i testi nella schermata con quelli veri del database!
    fun impostaDatiUtente(nomeReale: String, emailReale: String) {
        _nomeUtente.value = nomeReale
        _emailUtente.value = emailReale
    }
}

// MODIFICA 2: Questa è la nostra "finta memoria" che simula il database (deve stare fuori dalla classe)
object SessionManager {
    var nomeUtenteAttuale = "Nuovo Utente"
    var emailUtenteAttuale = "utente@studio.unibo.it"
}