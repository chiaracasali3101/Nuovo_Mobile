package com.unibo.android.android.data.remote

import com.google.gson.annotations.SerializedName
import model.MovieDto

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>
)