package com.tamayo.jetmovies.domain.usecases

import com.tamayo.jetmovies.data.rest.MoviesRepository
import com.tamayo.jetmovies.domain.mappers.MoviesDomain
import com.tamayo.jetmovies.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Popular @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(page: Byte): Flow<UIState<List<MoviesDomain>>> = flow {
        moviesRepository.getPopularMovies(page).collect {
            emit(it)
        }
    }
}