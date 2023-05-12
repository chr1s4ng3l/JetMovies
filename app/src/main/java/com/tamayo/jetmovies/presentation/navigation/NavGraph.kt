package com.tamayo.jetmovies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tamayo.jetmovies.presentation.ui.DetailsScreen
import com.tamayo.jetmovies.presentation.ui.HomeScreen
import com.tamayo.jetmovies.presentation.viewmodel.MoviesViewModel

@Composable
fun NavGraph(
    moviesViewModel: MoviesViewModel,
    nav: NavHostController
) {
    NavHost(navController = nav, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            HomeScreen(moviesViewModel, navController = nav)
        }

        composable(Routes.DetailsScreen.route) {
            DetailsScreen(moviesViewModel)
        }

    }

}