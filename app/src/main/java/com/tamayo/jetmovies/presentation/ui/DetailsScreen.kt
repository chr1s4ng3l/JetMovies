package com.tamayo.jetmovies.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tamayo.jetmovies.R
import com.tamayo.jetmovies.domain.mappers.DetailsDomain
import com.tamayo.jetmovies.domain.mappers.VideoDomain
import com.tamayo.jetmovies.presentation.viewmodel.MoviesViewModel
import com.tamayo.jetmovies.utils.UIState
import com.tamayo.jetmovies.utils.YouTubePlayer


@Composable
fun DetailsScreen(
    moviesViewModel: MoviesViewModel,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        val movie = moviesViewModel.selectMovie

        moviesViewModel.getVideo(movie?.id ?: 1111)
        moviesViewModel.getMovieDetails(movie?.id ?: 1111)

        when (val state = moviesViewModel.movie.collectAsState(UIState.LOADING).value) {
            is UIState.LOADING -> {}
            is UIState.SUCCESS -> {
                when (val videoState =
                    moviesViewModel.video.collectAsState(UIState.LOADING).value) {
                    is UIState.LOADING -> {}
                    is UIState.SUCCESS -> {

                        MovieDetail(
                            detailsDomain = state.data,
                            videoDomain = videoState.data.first()
                        )
                    }

                    is UIState.ERROR -> {
                        Toast.makeText(
                            LocalContext.current,
                            "Check your internet connection",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }

            is UIState.ERROR -> {
                Toast.makeText(
                    LocalContext.current,
                    "Check your internet connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


    }
}


@Composable
fun MovieDetail(detailsDomain: DetailsDomain? = null, videoDomain: VideoDomain? = null) {

    val rating = detailsDomain?.voteAverage?.times(10)?.toString()?.substring(0, 2)

    val genre = detailsDomain?.genres?.first()?.name

    YouTubePlayer(videoDomain?.key ?: "UJZx8MayWxk", modifier = Modifier.fillMaxWidth())

    Text(
        modifier = Modifier.padding(4.dp),
        text = detailsDomain?.title ?: "Title not found",
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp,
        color = Color.White
    )

    Spacer(modifier = Modifier.size(4.dp))

    Row {
        Text(
            modifier = Modifier.padding(4.dp),
            text = "${rating.toString()}%",
            color = Color.Green,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            modifier = Modifier.padding(4.dp),
            text = detailsDomain?.releaseDate.toString(),
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            modifier = Modifier.padding(4.dp),
            text = genre.toString(),
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )

    }

    Text(
        modifier = Modifier.padding(4.dp),
        text = detailsDomain?.tagline.toString(),
        color = Color.White,
        fontWeight = FontWeight.ExtraBold
    )

    Text(
        modifier = Modifier.padding(4.dp),
        text = detailsDomain?.overview ?: "Description not available", color = Color.White
    )

    AsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://www.themoviedb.org/t/p/w1280${detailsDomain?.backdropPath}")
            .crossfade(true).build(),
        placeholder = painterResource(R.drawable.movieplaceholder),
        error = painterResource(id = R.drawable.movieplaceholder),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}

