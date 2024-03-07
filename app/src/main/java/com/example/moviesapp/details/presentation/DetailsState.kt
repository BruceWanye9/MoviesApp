package com.example.moviesapp.details.presentation

import com.ahmedapps.moviesapp.movieList.domain.model.Movie

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)
