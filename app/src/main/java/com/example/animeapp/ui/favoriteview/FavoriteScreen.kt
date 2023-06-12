package com.example.animeapp.ui.favoriteview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.animeapp.model.navigation.components.AppBar
import com.example.animeapp.ui.searchview.SearchScreenContent

@Composable
fun FavoriteScreen() {

    Scaffold(
        topBar = {
            AppBar()
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()){
                Text(text = "Favorite Screen")
            }
        }
    }

}