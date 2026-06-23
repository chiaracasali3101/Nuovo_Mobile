package com.unibo.android.ui.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.unibo.android.corsolp2526.data.model.MovieDto
import com.unibo.android.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClassificaViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _topRatedMovies = MutableStateFlow<List<MovieDto>>(emptyList())
    val topRatedMovies: StateFlow<List<MovieDto>> = _topRatedMovies.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val movies = repository.getTopRatedMovies()
                _topRatedMovies.value = movies
            } catch (e: Exception) {
                e.printStackTrace()
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