package com.tamayo.jetmovies.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.tamayo.jetmovies.presentation.navigation.NavGraph
import com.tamayo.jetmovies.presentation.viewmodel.MoviesViewModel
import com.tamayo.jetmovies.ui.theme.JetMoviesTheme
import com.tamayo.jetmovies.ui.theme.NetflixBackground
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetMoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = NetflixBackground
                ) {

                    val nav = rememberNavController()

                    val moviesViewModel: MoviesViewModel = hiltViewModel()

                    NavGraph(moviesViewModel, nav)

                }
            }
        }
    }
}
