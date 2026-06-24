/*package com.unibo.android.data.di

import android.R.attr.apiKey
import com.unibo.android.data.repositories.AccommodationRepositoryImpl
import com.unibo.android.data.repositories.MovieRepositoryImpl
import com.unibo.android.domain.di.RepositoryProvider
import com.unibo.android.domain.repositories.AccommodationRepository
import com.unibo.android.domain.repositories.FilmRepository
import com.unibo.android.domain.repositories.MovieRepository

class RepositoryProviderImpl: RepositoryProvider {
    val movieRepository: MovieRepository = MovieRepositoryImpl(api, apiKey)
    companion object {
        val movieRepository: MovieRepository
        //val movieRepository: FilmRepository
    }
}
 */






/*
package com.unibo.android.android.data.di

import com.unibo.android.corsolp2526.data.api.TmdbApi
import com.unibo.android.data.repositories.MovieRepositoryImpl
import com.unibo.android.domain.repositories.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryProviderImpl : RepositoryProvider {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val tmdbApi = retrofit.create(TmdbApi::class.java)

        private const val API_KEY = "LA_VOSTRA_API_KEY_QUI"

        override val movieRepository: MovieRepository = MovieRepositoryImpl(
            api = tmdbApi,
            apiKey = API_KEY
        )
    }
}
 */

package com.unibo.android.corsolp2526.data.repository

import com.unibo.android.corsolp2526.data.api.RetrofitClient
import com.unibo.android.domain.repositories.MovieRepository

object RepositoryProviderImpl {

    fun getMovieRepository(): MovieRepository {
        return MovieRepositoryImpl(
            api = RetrofitClient.tmdbApi,
            apiKey = "ff7d85461d0b3707a3ff91e938da61d2"
        )
    }
}