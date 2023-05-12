package com.tamayo.jetmovies.data.rest

import com.tamayo.jetmovies.data.details.DetailsResponse
import com.tamayo.jetmovies.data.movies.MoviesResponse
import com.tamayo.jetmovies.data.video.VideoResponse
import com.tamayo.jetmovies.domain.mappers.DetailsDomain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET(END_POINT_POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Byte = 1
    ): Response<MoviesResponse>

    @GET(END_POINT_UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Byte = 1
    ): Response<MoviesResponse>

    @GET(END_POINT_NOW_PLAYING)
    suspend fun getNowPLayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Byte = 1
    ): Response<MoviesResponse>

    //https://api.themoviedb.org/3/movie/758323?api_key=9c228d5069250759d7c308240d11c8e6&language=en-US
    //https://api.themoviedb.org/3/movie/758323/videos?api_key=819950d4cf35be1fb70d8746bc0796bf&language=en-US
    @GET(END_POINT_MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<DetailsResponse>
   @GET(END_POINT_MOVIE_VIDEO)
    suspend fun getMovieVideo(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<VideoResponse>


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val END_POINT_POPULAR = "popular"
        const val END_POINT_NOW_PLAYING = "now_playing"
        const val END_POINT_UPCOMING = "upcoming"
        const val END_POINT_MOVIE_DETAILS = "{movie_id}"
        const val END_POINT_MOVIE_VIDEO = "{movie_id}/videos"
        const val API_KEY = "9c228d5069250759d7c308240d11c8e6"
        const val LANGUAGE = "en-US"
    }
}