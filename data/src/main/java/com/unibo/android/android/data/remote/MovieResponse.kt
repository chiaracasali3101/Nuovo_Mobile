package com.unibo.android.android.data.remote

import com.google.gson.annotations.SerializedName
import com.unibo.android.corsolp2526.data.model.MovieDto

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>
)