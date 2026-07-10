package com.unibo.android.corsolp2526.data.repository

import android.content.Context
import com.unibo.android.android.data.local.FilmDatabase
import com.unibo.android.android.data.repositories.FilmRepositoryImpl
import com.unibo.android.corsolp2526.data.api.RetrofitClient
import com.unibo.android.data.remote.FilmApiService
import com.unibo.android.domain.repositories.MovieRepository

object RepositoryProviderImpl {

    fun getMovieRepository(context: Context, apiKey: String): MovieRepository {
        val database = FilmDatabase.getDatabase(context)

        return FilmRepositoryImpl(
            filmDao = database.filmDao(),
            apiService = RetrofitClient.tmdbApi as FilmApiService,
            apiKey = apiKey
        )
    }
}