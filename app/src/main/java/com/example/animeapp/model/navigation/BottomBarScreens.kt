package com.example.animeapp.model.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Stream
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object SearchScreen : BottomBarScreens(
        route = "SearchScreen",
        title = "Search Anime",
        icon = Icons.Default.CatchingPokemon
    )

    object FavoriteScreen : BottomBarScreens(
        route = "FavoriteScreen",
        title = "Favorites Anime",
        icon = Icons.Default.Stream
    )

    object DetailsScreen : BottomBarScreens(
        route = "DetailsScreen",
        title = "Detail Anime",
        icon = Icons.Default.List
    )

    object CharacterScreen : BottomBarScreens(
        route = "CharacterScreen",
        title = "Character Anime",
        icon = Icons.Default.Person
    )

}