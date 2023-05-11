package com.tamayo.jetmovies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tamayo.jetmovies.presentation.DetailsScreen
import com.tamayo.jetmovies.presentation.HomeScreen

@Composable
fun NavGraph() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            HomeScreen()
        }

        composable(Routes.DetailsScreen.route) {
            DetailsScreen()
        }

    }

}