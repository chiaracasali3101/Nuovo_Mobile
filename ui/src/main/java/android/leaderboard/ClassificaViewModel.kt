package android.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.unibo.android.domain.models.Film
import com.unibo.android.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update

class ClassificaViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _topRatedMovies = MutableStateFlow<List<Film>>(emptyList())
    val topRatedMovies: StateFlow<List<Film>> = _topRatedMovies.asStateFlow()
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val movies: List<Film> = repository.getTopRatedMovies()
                _topRatedMovies.update { movies }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

class ClassificaViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClassificaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClassificaViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel sconosciuta")
    }
}