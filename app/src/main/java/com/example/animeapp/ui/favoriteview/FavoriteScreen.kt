package com.example.animeapp.ui.favoriteview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animeapp.R
import com.example.animeapp.model.navigation.components.AppBar
import com.example.animeapp.ui.favoriteview.component.FavoriteAnimeItem
import com.example.animeapp.ui.favoriteview.component.isLandscape
import com.example.animeapp.ui.favoriteview.viewmodel.FavoriteViewModel
import com.example.animeapp.ui.searchview.SearchScreenContent
import com.example.domain.room.model.FavoriteAnime
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favUiState by favoriteViewModel.uiStateFavorite.collectAsState()

    FavoriteScreenContent(
        favoriteAnimeFlow = favUiState.favoriteAnimesList,
        removeFromFavorites = favUiState.removeFavoriteAnime
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FavoriteScreenContent (
    favoriteAnimeFlow: StateFlow<List<FavoriteAnime>>,
    removeFromFavorites: (Int) -> Unit,
) {

    val favoriteArticles by favoriteAnimeFlow.collectAsState(initial = emptyList())
    val pagerState = rememberPagerState()
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(4)
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val isLandscape = isLandscape()
    val cardWidthFactor = if (isLandscape) 0.4f else 0.7f
    val cardHeightFactor = if (isLandscape) 0.5f else 0.7f
    val cardWidth = screenWidth * cardWidthFactor
    val cardHeight = screenHeight * cardHeightFactor
    val padding = (screenWidth - cardWidth) / 2

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),

        ) {
        HorizontalPager(
            pageCount = if (favoriteArticles.isEmpty()) 1 else favoriteArticles.size,
            state = pagerState,
            flingBehavior = fling,
            contentPadding = PaddingValues(start = padding, end = padding)
        ) { page ->
            if (favoriteArticles.isEmpty()) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                val favoriteArticle = favoriteArticles[page]
                FavoriteAnimeItem(
                    favoriteArticle = favoriteArticle,
                    pagerState = pagerState,
                    currentPage = page,
                    modifier = Modifier.size(cardWidth, cardHeight),
                    removeFromFavorites = { article -> removeFromFavorites(article.id) },
                )
            }
        }
    }
}