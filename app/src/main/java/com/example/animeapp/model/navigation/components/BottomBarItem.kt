package com.example.animeapp.model.navigation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.animeapp.R
import com.example.animeapp.model.navigation.BottomBarScreens

@Composable
fun RowScope.BottomBarItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = screen.title
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "${R.string.navigation_description}"
            )
        },
        selected = currentDestination?.hierarchy?.any { destiny ->
            destiny.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )

}