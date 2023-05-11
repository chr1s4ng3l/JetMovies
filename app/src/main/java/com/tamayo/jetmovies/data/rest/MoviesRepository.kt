package com.tamayo.jetmovies.data.rest

import com.tamayo.jetmovies.domain.MoviesDomain
import com.tamayo.jetmovies.domain.mapToDomain
import com.tamayo.jetmovies.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface MoviesRepository {

    fun getPopularMovies(page: Byte): Flow<UIState<List<MoviesDomain>>>
    fun getUpcomingMovies(page: Byte): Flow<UIState<List<MoviesDomain>>>
    fun getNowPlayingMovies(page: Byte): Flow<UIState<List<MoviesDomain>>>
    // fun getMovieDetails(id: String): Flow<UIState<List<MoviesDomain>>>

}

class MoviesRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val ioDispatcher: CoroutineDispatcher
) : MoviesRepository {

    override fun getPopularMovies(page: Byte): Flow<UIState<List<MoviesDomain>>> =
        flow<UIState<List<MoviesDomain>>> {
            emit(UIState.LOADING)

            try {
                val response = serviceApi.getPopularMovies(page = page)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emit(UIState.SUCCESS(body.results.mapToDomain()))
                    }

                } else {
                    throw (Exception("Response was null"))
                }

            } catch (e: Exception) {
                emit(UIState.ERROR(e))

            }

        }.flowOn(ioDispatcher)

    override fun getUpcomingMovies(page: Byte): Flow<UIState<List<MoviesDomain>>> =
        flow<UIState<List<MoviesDomain>>> {
            emit(UIState.LOADING)

            try {
                val response = serviceApi.getUpcomingMovies(page = page)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emit(UIState.SUCCESS(body.results.mapToDomain()))
                    }

                } else {
                    throw (Exception("Response was null"))
                }

            } catch (e: Exception) {
                emit(UIState.ERROR(e))

            }

        }.flowOn(ioDispatcher)

    override fun getNowPlayingMovies(page: Byte): Flow<UIState<List<MoviesDomain>>> =
        flow<UIState<List<MoviesDomain>>> {
            emit(UIState.LOADING)

            try {
                val response = serviceApi.getNowPLayingMovies(page = page)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emit(UIState.SUCCESS(body.results.mapToDomain()))
                    }

                } else {
                    throw (Exception("Response was null"))
                }

            } catch (e: Exception) {
                emit(UIState.ERROR(e))

            }

        }.flowOn(ioDispatcher)

}