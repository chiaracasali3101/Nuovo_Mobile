package com.unibo.android.corsolp2526.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.unibo.android.corsolp2526.data.model.MovieResponse
interface TmdbApi {
    //laura
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "it-IT"
    ): MovieResponse

   /* @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "it-IT"
    ): MovieResponse*/

    //chiara
    /*@GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "it-IT"
    ): MovieResponse*/

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "it-IT"
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "it-IT"
    ): MovieResponse
}

