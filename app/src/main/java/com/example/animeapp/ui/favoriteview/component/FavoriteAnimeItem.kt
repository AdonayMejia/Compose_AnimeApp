package com.example.animeapp.ui.favoriteview.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.domain.room.model.FavoriteAnime
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteAnimeItem(
    favoriteArticle: FavoriteAnime,
    pagerState: PagerState,
    currentPage: Int,
    modifier: Modifier = Modifier,
    removeFromFavorites: (FavoriteAnime) -> Unit,
) {

    val pageOffset = (
            (pagerState.currentPage - currentPage) + pagerState.currentPageOffsetFraction).absoluteValue

    val scale = lerp(
        start = 0.8f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(
            0f,
            1f
        )
    )

    Card(
        modifier = modifier
            .scale(scale)
            .animateContentSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(R.drawable.naruto),
                    contentDescription = "Pager Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(model = favoriteArticle.animeBanner),
                    contentDescription = "Pager Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }

            IconButton(
                onClick = { removeFromFavorites(favoriteArticle) },
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.CloudOff,
                    contentDescription = "Delete Anime",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PreviewFavoriteAnimeItem() {
    val favoriteAnime = FavoriteAnime(
        id = 1,
        animeTitle = "Anime Title",
        animeBanner = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
    )

    val pagerState = rememberPagerState()
    AnimeAppTheme {
        FavoriteAnimeItem(
            favoriteArticle = favoriteAnime,
            pagerState = pagerState,
            currentPage = 1,
            removeFromFavorites = { /* Do something here */ }
        )
    }

}
