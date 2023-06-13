package com.example.animeapp.ui.searchview.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.ui.favoriteview.component.isLandscape
import com.example.domain.search.model.AnimeModel


@Composable
fun AnimeItem(
    anime: AnimeModel,
    onFavoriteSelected: (AnimeModel) -> Unit,
    animeFav: Set<Int>,
    navController: NavHostController,
) {
    val painter = rememberAsyncImagePainter(model = anime.image)
    val isFavorite = animeFav.contains(anime.id)
    Card(
        modifier = Modifier
            .aspectRatio(if (isLandscape()) 2 / 2f else 3 / 5f)
            .padding(5.dp)
            .clickable {
                navController.navigate("DetailsScreen/${anime.id}")
            },
        shape = RectangleShape,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (LocalInspectionMode.current) {
                Image(
                    modifier = Modifier.aspectRatio(3 / 4f),
                    painter = painterResource(id = R.drawable.naruto),
                    contentDescription = "Image",
                    alignment = Alignment.Center
                )
            } else {
                Image(
                    modifier = if (isLandscape()) Modifier.aspectRatio(2 / 1f) else Modifier.aspectRatio(
                        3 / 4f
                    ),
                    painter = painter,
                    contentDescription = "Image",
                    alignment = Alignment.Center
                )
            }
            Text(
                text = anime.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
            )
            IconButton(onClick = { onFavoriteSelected(anime) }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Red
                )
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ItemPreview() {
    val navController = rememberNavController()

    val anime = AnimeModel(
        id = 1,
        name = "Naruto Shippuden",
        image = R.drawable.naruto.toString()
    )
    AnimeItem(
        anime = anime,
        onFavoriteSelected = { /* Handle Event */ },
        animeFav = setOf(1),
        navController = navController
    )
}
