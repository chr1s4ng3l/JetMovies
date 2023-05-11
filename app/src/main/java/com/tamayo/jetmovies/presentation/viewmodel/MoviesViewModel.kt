package com.tamayo.jetmovies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.jetmovies.domain.MoviesDomain
import com.tamayo.jetmovies.domain.usecases.NowPlaying
import com.tamayo.jetmovies.domain.usecases.Popular
import com.tamayo.jetmovies.domain.usecases.Upcoming
import com.tamayo.jetmovies.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val popular: Popular,
    private val upcoming: Upcoming,
    private val nowPlaying: NowPlaying
) : ViewModel() {

    private val _popularMovie: MutableStateFlow<UIState<List<MoviesDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val popularMovie: StateFlow<UIState<List<MoviesDomain>>> get() = _popularMovie.asStateFlow()

    private val _nowPlayingMovie: MutableStateFlow<UIState<List<MoviesDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val nowPlayingMovie: StateFlow<UIState<List<MoviesDomain>>> get() = _nowPlayingMovie.asStateFlow()

    private val _upcomingMovie: MutableStateFlow<UIState<List<MoviesDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val upcomingMovie: StateFlow<UIState<List<MoviesDomain>>> get() = _upcomingMovie.asStateFlow()


    fun getPopularMovies(page: Byte) = viewModelScope.launch {

        popular.invoke(page).collect {
            _popularMovie.update { it }
        }
    }

    fun getNowPlayingMovies(page: Byte) = viewModelScope.launch {

        nowPlaying.invoke(page).collect {

            _nowPlayingMovie.update { it }
        }
    }

    fun getUpcomingMovies(page: Byte) = viewModelScope.launch {

        upcoming.invoke(page).collect {
            _upcomingMovie.update { it }
        }
    }


}