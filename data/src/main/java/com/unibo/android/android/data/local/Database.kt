package com.unibo.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.unibo.android.data.entities.FilmEntity

import com.unibo.android.data.local.FilmDao

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class Film : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}