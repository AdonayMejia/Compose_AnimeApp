package com.example.animeapp.model.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    favoriteIconCLick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "AnimeLand")
        },
        actions = {
            IconButton(
                onClick = favoriteIconCLick
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Anime",
                    tint = Color.Red
                )
            }
        }
    )
}