package com.tamayo.jetmovies.data.rest

import com.tamayo.jetmovies.data.movies.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET(END_POINT_POPULAR)
    suspend fun getPopularMovies(
        @Query("page") page: Byte = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET(END_POINT_UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("page") page: Byte = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET(END_POINT_NOW_PLAYING)
    suspend fun getNowPLayingMovies(
        @Query("page") page: Byte = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET(END_POINT_MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val END_POINT_POPULAR = "popular"
        const val END_POINT_NOW_PLAYING = "now_playing"
        const val END_POINT_UPCOMING = "upcoming"
        const val END_POINT_MOVIE_DETAILS = "{movie_id}"
        const val API_KEY = "api_key=9c228d5069250759d7c308240d11c8e6"
        const val LANGUAGE = "en-US"
    }
}