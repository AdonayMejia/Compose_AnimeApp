package com.example.animeapp.model.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animeapp.ui.favoriteview.FavoriteScreen
import com.example.animeapp.ui.searchview.SearchAnimeScreen
import com.example.animeapp.ui.searchview.viewmodel.SearchViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    val searchViewModel: SearchViewModel = hiltViewModel()
//    val favoriteViewModel: FavoriteViewModel = getViewModel()

    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.SearchScreen.route
    ) {
        composable(route = BottomBarScreens.SearchScreen.route) {
            SearchAnimeScreen(
                viewModel = searchViewModel
            )
        }
        composable(route = BottomBarScreens.FavoriteScreen.route) {
            FavoriteScreen(
            )
        }
//        composable(
//            route = BottomBarScreen.DetailScreen.route,
//            arguments = listOf(navArgument("url") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val url = backStackEntry.arguments?.getString("url") ?: ""
//            DetailScreen(webUrl = url)
//        }
    }
}