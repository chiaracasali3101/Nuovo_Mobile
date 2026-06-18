package com.unibo.android.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.unibo.android.data.entities.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    //lettura
    @Query("SELECT * FROM watchlist")
    fun getTuttiIFilm(): Flow<List<FilmEntity>>

    //inserimento
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun addWatchlist(movie: FilmEntity)

    //eliminazione
    @Delete
    suspend fun removeWatchlist(movie: FilmEntity)

    //controllo
    @Query("SELECT EXISTS(SELECT * FROM watchlist WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    //ricerca
    @Query("SELECT * FROM watchlist WHERE titolo LIKE '%' || :string || '%' ")
    fun getFilmsByQuery(string: String): List<FilmEntity>

    @Query("SELECT * FROM watchlist WHERE id = :id")
    suspend fun getFilmById(id: Int): FilmEntity?
}