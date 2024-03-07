package com.example.moviesapp.movielist.data.remote

import android.media.MediaRouter.RouteCategory
import com.example.moviesapp.movielist.data.remote.respond.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{category}")
    suspend fun getMoviesList(
@Path("category") category: String,
@Query("page") page:Int,
@Query("api_key") apiKey:String= Api_Key
    ):MovieListDto
    companion object{
        const val Base_Url="https://api.themoviedb.org/3/"
        const val Image_Base_Url="https://image.tmdb.org/t/p/w500"
        const val Api_Key="d7108e1098b90ba84fd2fc4a2f5c10fe"
    }
}