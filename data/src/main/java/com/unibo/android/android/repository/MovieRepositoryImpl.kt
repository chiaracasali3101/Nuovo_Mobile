package com.unibo.android.corsolp2526.data.repository

import com.unibo.android.corsolp2526.data.api.TmdbApi
import com.unibo.android.corsolp2526.data.model.MovieDto
import com.unibo.android.corsolp2526.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: TmdbApi,
    private val apiKey: String
) : MovieRepository {

    override suspend fun getPopularMovies(): List<MovieDto> {
        val response = api.getPopularMovies(apiKey)
        return response.results
    }

    override suspend fun getTopRatedMovies(): List<MovieDto> {
        val response = api.getTopRatedMovies(apiKey)
        return response.results
    }
}