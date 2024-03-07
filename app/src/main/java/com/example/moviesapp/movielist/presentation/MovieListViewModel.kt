package com.example.moviesapp.movielist.presentation

import MovieListRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.movielist.util.Catergory
import com.example.moviesapp.movielist.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {
    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getPopularMovieList()
        getUpcomingMovieList()
    }

    fun onEvent(event: MovieListEvent) {
        when (event) {
            MovieListEvent.Navigate -> {
                _movieListState.update {
                    it.copy(
                        isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen
                    )
                }
            }

            is MovieListEvent.pageinate -> {
                if (event.category == Catergory.POPULAR) {
                    getPopularMovieList()
                } else if (event.category == Catergory.UpCOMING) {
                    getUpcomingMovieList()
                }
            }
        }

    }

    private fun getPopularMovieList() {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            val forceFetchFromRemote = false// change this
            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Catergory.POPULAR,
                movieListState.value.popularMovieListPage
            )
                .collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {
                            _movieListState.update {
                                it.copy(isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            _movieListState.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }

                        is Resource.Success -> {
                            result.data?.let { popularList ->
                                _movieListState.update {
                                    it.copy(
                                        popularMovieList = movieListState.value.popularMovieList +
                                                popularList.shuffled(),
                                        popularMovieListPage = movieListState.value.popularMovieListPage
                                    )
                                }

                            }
                        }
                    }
                }
        }
    }

    private fun getUpcomingMovieList() {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            val forceFetchFromRemote = false// change this
            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Catergory.UpCOMING,
                movieListState.value.upcomingMoviePage
            )
                .collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {
                            _movieListState.update {
                                it.copy(isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            _movieListState.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }

                        is Resource.Success -> {
                            result.data?.let { upcomingList ->
                                _movieListState.update {
                                    it.copy(
                                        upcomingMovieList = movieListState.value.upcomingMovieList +
                                                upcomingList.shuffled(),
                                        upcomingMoviePage = movieListState.value.upcomingMoviePage
                                    )
                                }

                            }
                        }
                    }
                }
        }
    }
}

