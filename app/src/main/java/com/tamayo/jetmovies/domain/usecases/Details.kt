package com.tamayo.jetmovies.domain.usecases

import com.tamayo.jetmovies.data.rest.MoviesRepository
import com.tamayo.jetmovies.domain.mappers.DetailsDomain
import com.tamayo.jetmovies.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Details @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<UIState<DetailsDomain>> = flow {
        moviesRepository.getMovieDetails(id).collect{
            emit(it)
        }

    }
}