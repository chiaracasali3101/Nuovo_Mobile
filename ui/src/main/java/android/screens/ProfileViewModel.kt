package android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.FilmRepository
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

    // Watchlist: prende i film aggiunti al database che NON sono ancora stati visti (visto è false)
    val watchlist: StateFlow<List<Film>> = tuttiIFilm
        .map { lista -> lista.filter { !it.visto } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}