package com.bigbigdreams.dreamax.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.bigbigdreams.dreamax.R
import com.bigbigdreams.dreamax.ui.downloads.DownloadsScreen
import com.bigbigdreams.dreamax.ui.gamedetails.GameDetailsScreen
import com.bigbigdreams.dreamax.ui.home.HomeScreen
import com.bigbigdreams.dreamax.ui.mygames.MyGamesScreen
import com.bigbigdreams.dreamax.ui.profile.ProfileScreen
import com.bigbigdreams.dreamax.ui.store.StoreScreen
import com.bigbigdreams.dreamax.ui.theme.DreamaxBlack
import com.bigbigdreams.dreamax.ui.theme.DreamaxLightGrey
import com.bigbigdreams.dreamax.ui.theme.DreamaxRed
import com.bigbigdreams.dreamax.ui.theme.DreamaxWhite

data class BottomNavItem(
    val screen: Screen,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, R.string.nav_home, Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem(Screen.Store, R.string.nav_store, Icons.Filled.Store, Icons.Outlined.Store),
    BottomNavItem(Screen.Downloads, R.string.nav_downloads, Icons.Filled.Download, Icons.Outlined.Download),
    BottomNavItem(Screen.MyGames, R.string.nav_my_games, Icons.Filled.SportsEsports, Icons.Outlined.SportsEsports),
    BottomNavItem(Screen.Profile, R.string.nav_profile, Icons.Filled.Person, Icons.Outlined.Person)
)

@Composable
fun DreaMaxNavGraph(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.route?.startsWith("game_details") != true

    Scaffold(
        containerColor = DreamaxBlack,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(containerColor = DreamaxBlack, contentColor = DreamaxWhite) {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination?.hierarchy?.any {
                            it.route == item.screen.route
                        } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = stringResource(item.labelRes)
                                )
                            },
                            label = { Text(text = stringResource(item.labelRes)) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DreamaxRed,
                                selectedTextColor = DreamaxRed,
                                unselectedIconColor = DreamaxLightGrey,
                                unselectedTextColor = DreamaxLightGrey,
                                indicatorColor = DreamaxBlack
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController = navController) }
            composable(Screen.Store.route) { StoreScreen(navController = navController) }
            composable(Screen.Downloads.route) { DownloadsScreen(navController = navController) }
            composable(Screen.MyGames.route) { MyGamesScreen(navController = navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController = navController) }
            composable(
                route = Screen.GameDetails.route,
                arguments = listOf(navArgument("gameId") { type = NavType.StringType })
            ) { backStackEntry ->
                val gameId = backStackEntry.arguments?.getString("gameId") ?: return@composable
                GameDetailsScreen(navController = navController, gameId = gameId)
            }
        }
    }
}
