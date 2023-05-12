package com.tamayo.jetmovies.data.rest

import android.util.Log
import com.tamayo.jetmovies.domain.mappers.DetailsDomain
import com.tamayo.jetmovies.domain.mappers.MoviesDomain
import com.tamayo.jetmovies.domain.mappers.VideoDomain
import com.tamayo.jetmovies.domain.mappers.mapToDomain
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
    fun getMovieDetails(id: Int): Flow<UIState<DetailsDomain>>
    fun getVideo(id: Int): Flow<UIState<List<VideoDomain>>>

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

    override fun getMovieDetails(id: Int): Flow<UIState<DetailsDomain>> =
        flow<UIState<DetailsDomain>> {
            emit(UIState.LOADING)

            try {
                val response = serviceApi.getMovieDetails(movieId = id)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        Log.d("LOL", "getMovieDetails: ->>> $body ")
                        emit(UIState.SUCCESS(body.mapToDomain()))
                    } ?: throw Exception("Body was null")

                } else {
                    throw (Exception("Response was null"))
                }

            } catch (e: Exception) {
                emit(UIState.ERROR(e))

            }

        }.flowOn(ioDispatcher)


    override fun getVideo(id: Int): Flow<UIState<List<VideoDomain>>> =
        flow<UIState<List<VideoDomain>>> {
            emit(UIState.LOADING)

            try {
                val response = serviceApi.getMovieVideo(movieId = id)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emit(UIState.SUCCESS(body.results.mapToDomain()))
                    } ?: throw (Exception("Response was null"))

                } else {
                    throw (Exception("Response was null"))
                }

            } catch (e: Exception) {
                emit(UIState.ERROR(e))

            }

        }.flowOn(ioDispatcher)


}