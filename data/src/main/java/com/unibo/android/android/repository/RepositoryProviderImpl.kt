package com.unibo.android.corsolp2526.data.repository

import com.unibo.android.corsolp2526.domain.repository.MovieRepository
import com.unibo.android.corsolp2526.data.api.RetrofitClient

object RepositoryProviderImpl {

    fun getMovieRepository(): MovieRepository {
        return MovieRepositoryImpl(
            api = RetrofitClient.tmdbApi,
            apiKey = "ff7d85461d0b3707a3ff91e938da61d2"
        )
    }
}