package com.example.animeapp.model.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animeapp.ui.characterview.CharacterScreen
import com.example.animeapp.ui.detailview.DetailScreen
import com.example.animeapp.ui.favoriteview.FavoriteScreen
import com.example.animeapp.ui.searchview.SearchAnimeScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.SearchScreen.route
    ) {
        composable(route = BottomBarScreens.SearchScreen.route) {
            SearchAnimeScreen(
                onAnimeSelected = { id ->
                    navController.navigate("DetailsScreen/$id")
                },
                onMoveFavorite = {
                    navController.navigate(BottomBarScreens.FavoriteScreen.route)
                }
            )
        }
        composable(route = BottomBarScreens.FavoriteScreen.route) {
            FavoriteScreen()
        }
        composable(
            route = BottomBarScreens.DetailsScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let { id ->
                DetailScreen(id = id, navController = navController)
            }
        }
        composable(
            route = BottomBarScreens.CharacterScreen.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("characterId")?.let { id ->
                CharacterScreen( characterId = id, navController = navController)
            }
        }
    }
}