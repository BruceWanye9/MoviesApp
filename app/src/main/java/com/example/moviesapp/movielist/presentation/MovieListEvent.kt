package com.example.moviesapp.movielist.presentation

 sealed interface MovieListEvent {
     data class  pageinate(val category :String):MovieListEvent
     object Navigate:MovieListEvent
}