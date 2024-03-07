package com.example.moviesapp.movielist.util

sealed class Screen (val rout:String) {
    object Home:Screen("main")
    object PopularMovieList:Screen("popularMovie")
    object upcomingMovieList:Screen("upcomingMovie")
    object Details:Screen("details")
}