package com.unibo.android.domain.models

data class UserProfile(
    val nome: String,
    val email: String,
    val uriImmagine: String? = null,
    val latitudine: Double? = null,
    val longitudine: Double? = null
)