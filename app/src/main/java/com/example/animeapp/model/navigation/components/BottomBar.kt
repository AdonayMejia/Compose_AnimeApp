package com.example.animeapp.model.navigation.components

import android.content.ClipData
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.animeapp.model.navigation.BottomBarScreens

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreens.SearchScreen,
        BottomBarScreens.FavoriteScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            BottomBarItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}