package com.example.inviochallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inviochallenge.data.model.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}