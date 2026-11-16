package com.bigbigdreams.dreamax.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object Store : Screen("store")
    data object Downloads : Screen("downloads")
    data object MyGames : Screen("my_games")
    data object Profile : Screen("profile")
    data object GameDetails : Screen("game_details/{gameId}") {
        fun createRoute(gameId: String) = "game_details/$gameId"
    }
}
