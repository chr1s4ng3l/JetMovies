package com.tamayo.jetmovies.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.jetmovies.domain.mappers.DetailsDomain
import com.tamayo.jetmovies.domain.mappers.MoviesDomain
import com.tamayo.jetmovies.domain.mappers.VideoDomain
import com.tamayo.jetmovies.domain.usecases.Details
import com.tamayo.jetmovies.domain.usecases.NowPlaying
import com.tamayo.jetmovies.domain.usecases.Popular
import com.tamayo.jetmovies.domain.usecases.Upcoming
import com.tamayo.jetmovies.domain.usecases.Videos
import com.tamayo.jetmovies.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val popular: Popular,
    private val upcoming: Upcoming,
    private val nowPlaying: NowPlaying,
    private val details: Details,
    private val videos: Videos
) : ViewModel() {

    var selectMovie: MoviesDomain? = null

    private val _popularMovie: MutableStateFlow<UIState<List<MoviesDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val popularMovie: StateFlow<UIState<List<MoviesDomain>>> get() = _popularMovie.asStateFlow()

    private val _nowPlayingMovie: MutableStateFlow<UIState<List<MoviesDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val nowPlayingMovie: StateFlow<UIState<List<MoviesDomain>>> get() = _nowPlayingMovie.asStateFlow()

    private val _upcomingMovie: MutableStateFlow<UIState<List<MoviesDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val upcomingMovie: StateFlow<UIState<List<MoviesDomain>>> get() = _upcomingMovie.asStateFlow()


    private val _movieDetails: MutableStateFlow<UIState<DetailsDomain>> =
        MutableStateFlow(UIState.LOADING)
    val movie: StateFlow<UIState<DetailsDomain>> get() = _movieDetails.asStateFlow()


    private val _video: MutableStateFlow<UIState<List<VideoDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val video: MutableStateFlow<UIState<List<VideoDomain>>> get() = _video


    fun getVideo(id: Int) = viewModelScope.launch {
        videos.invoke(id).collect {
            Log.d("Hola", "getMovieDetails: -> $it ")
            _video.value = it
        }
    }

    fun getMovieDetails(id: Int) = viewModelScope.launch {
        details.invoke(id).collect {
            _movieDetails.value = it
        }
    }

    fun getPopularMovies(page: Byte) = viewModelScope.launch {

        popular.invoke(page).collect {
            _popularMovie.value = it
        }
    }

    fun getNowPlayingMovies(page: Byte) = viewModelScope.launch {

        nowPlaying.invoke(page).collect {

            _nowPlayingMovie.value = it
        }
    }

    fun getUpcomingMovies(page: Byte) = viewModelScope.launch {

        upcoming.invoke(page).collect {
            _upcomingMovie.value = it
        }
    }


}