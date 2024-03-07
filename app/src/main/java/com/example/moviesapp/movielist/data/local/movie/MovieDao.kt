package com.example.moviesapp.movielist.data.local.movie

import androidx.room.Dao
import androidx.room.Upsert
import retrofit2.http.Query

@Dao
interface MovieDao  {
    @Upsert
    suspend fun upsertMovieList(movielist : List<MovieEntity>)//update and insert

    @androidx.room.Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @androidx.room.Query("SELECT * FROM MovieEntity WHERE category = :category")
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>

}