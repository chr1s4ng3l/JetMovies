package com.tamayo.jetmovies.presentation.navigation

sealed class Routes(val route: String){
    object HomeScreen: Routes("home")
    object DetailsScreen: Routes("details")
}
