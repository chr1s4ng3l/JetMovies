package com.tamayo.jetmovies.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tamayo.jetmovies.R
import com.tamayo.jetmovies.domain.mappers.MoviesDomain
import com.tamayo.jetmovies.presentation.navigation.Routes
import com.tamayo.jetmovies.presentation.viewmodel.MoviesViewModel
import com.tamayo.jetmovies.utils.UIState

@Composable
fun HomeScreen(vm: MoviesViewModel = hiltViewModel(),
               navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        vm.getPopularMovies(1)
        when (val state = vm.popularMovie.collectAsState(UIState.LOADING).value) {
            is UIState.LOADING -> {}
            is UIState.SUCCESS -> {
                MoviesList("Popular", state.data, navController) {
                    vm.selectMovie = it
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

        vm.getUpcomingMovies(1)
        when (val state = vm.upcomingMovie.collectAsState(UIState.LOADING).value) {
            is UIState.LOADING -> {}
            is UIState.SUCCESS -> {
                MoviesList("Upcoming", state.data, navController) {
                    vm.selectMovie = it
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

        vm.getNowPlayingMovies(1)
        when (val state = vm.nowPlayingMovie.collectAsState(UIState.LOADING).value) {
            is UIState.LOADING -> {}
            is UIState.SUCCESS -> {
                MoviesList("Now playing", state.data, navController) {
                    vm.selectMovie = it
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
fun MoviesList(
    title: String,
    movies: List<MoviesDomain>,
    navController: NavController? = null,
    selectedMovie: ((MoviesDomain) -> Unit)? = null
) {

    Text(
        modifier = Modifier.padding(8.dp),
        text = title,
        fontWeight = FontWeight.ExtraBold,
        color = Color.White,
        fontSize = 22.sp
    )

    LazyRow(content = {
        itemsIndexed(items = movies) { _, movie ->
            Movies(movie = movie, navController = navController, selectedMovie = selectedMovie)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Movies(
    movie: MoviesDomain,
    navController: NavController? = null,
    selectedMovie: ((MoviesDomain) -> Unit)? = null

) {

    Card(modifier = Modifier
        .width(150.dp)
        .height(200.dp)
        .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        onClick = {
            selectedMovie?.invoke(movie)
            navController?.navigate(Routes.DetailsScreen.route)
        }
    ) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://www.themoviedb.org/t/p/w1280${movie.posterPath}")
                .crossfade(true).build(),
            placeholder = painterResource(R.drawable.movieplaceholder),
            error = painterResource(id = R.drawable.movieplaceholder),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )


    }
}