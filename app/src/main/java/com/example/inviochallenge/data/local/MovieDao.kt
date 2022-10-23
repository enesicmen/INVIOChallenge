package com.example.inviochallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inviochallenge.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movieList: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie)

    @Query("SELECT * FROM Movie WHERE title LIKE '%' || :searchText || '%'")
    fun searchMovie(searchText: String): List<Movie>

    @Query("SELECT * FROM Movie WHERE movieId = :id")
    fun getMovieDetails(id: String): Movie?
}