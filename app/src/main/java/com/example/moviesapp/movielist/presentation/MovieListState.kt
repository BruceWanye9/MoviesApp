package com.example.moviesapp.movielist.presentation

import com.example.moviesapp.movielist.Domain.model.Movie

data class MovieListState(
    val isLoading:Boolean = false,
    val popularMovieListPage: Int=1,
    val upcomingMoviePage:Int =1,
    val isCurrentPopularScreen:Boolean=true,
    val popularMovieList :List<Movie> = emptyList(),
    val upcomingMovieList :List<Movie> = emptyList()
)
