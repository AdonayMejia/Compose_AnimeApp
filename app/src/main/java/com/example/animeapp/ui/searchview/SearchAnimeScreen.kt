package com.example.animeapp.ui.searchview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.model.navigation.BottomBarScreens
import com.example.animeapp.model.navigation.components.AppBar
import com.example.animeapp.ui.searchview.component.AnimeItem
import com.example.animeapp.ui.searchview.component.OptionsMenu
import com.example.animeapp.ui.searchview.component.SearchBar
import com.example.animeapp.ui.searchview.viewmodel.SearchViewModel
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchAnimeScreen(
    viewModel: SearchViewModel,
    navController: NavHostController,
) {
    val searchUiState by viewModel.uiState.collectAsState()
    val uiSTate by viewModel.favUiState.collectAsState()
    val anime = viewModel.animeFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppBar {
                navController.navigate(BottomBarScreens.FavoriteScreen.route)
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            SearchScreenContent(
                animeList = anime,
                onFavoriteSelected = { anime -> searchUiState.addToFavorites(anime) },
                animeFav = uiSTate.favAnime,
                onTypeChanged = searchUiState.onTypeChanged,
                onSortChanged = searchUiState.onSortChanged,
                onSearchChanged = searchUiState.onSearchChanged,
                navController = navController
            )
        }
    }
}

@Composable
fun SearchScreenContent(
    animeList: LazyPagingItems<AnimeModel>,
    onFavoriteSelected: (AnimeModel) -> Unit,
    animeFav: Set<Int>,
    onTypeChanged: (AnimeType) -> Unit,
    onSortChanged: (AnimeSort) -> Unit,
    onSearchChanged: (String) -> Unit,
    navController: NavHostController,

    ) {
    var selectedType by rememberSaveable { mutableStateOf(AnimeType.ANIME) }
    var selectedSort by rememberSaveable { mutableStateOf(AnimeSort.POPULARITY) }
    val onTypeChangedState by rememberUpdatedState(onTypeChanged)
    val onSortChangedState by rememberUpdatedState(onSortChanged)
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        SearchBar(
            searchQuery = searchQuery,
            onSearchChanged = {
                searchQuery = it
                onSearchChanged(it)
            }
        )
        OptionsMenu(
            animeType = selectedType,
            animeSort = selectedSort,
            animeTypeSelected = { type ->
                selectedType = type
                onTypeChangedState(type)
            },
            animeSortSelected = { sort ->
                selectedSort = sort
                onSortChangedState(sort)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (animeList.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No anime results",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        } else {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(animeList.itemCount) { anime ->
                    val animes = animeList[anime] ?: return@items
                    AnimeItem(
                        anime = animes,
                        onFavoriteSelected = { onFavoriteSelected(animes) },
                        animeFav = animeFav,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPrev() {
    val animes = flowOf(
        PagingData.from(
            listOf(
                AnimeModel(
                    id = 1,
                    name = "Naruto",
                    image = "https://m.media-amazon.com/images/M/MV5BODI2NjdlYWItMTE1ZC00YzI2LTlhZGQtNzE3NzA4MWM0ODYzXkEyXkFqcGdeQXVyNjU1OTg4OTM@._V1_.jpg",
                )
            )
        )
    ).collectAsLazyPagingItems()

    val navController = rememberNavController()
    AnimeAppTheme {
        SearchScreenContent(
            animeList = animes,
            onFavoriteSelected = {},
            animeFav = setOf(1),
            onTypeChanged = {},
            onSortChanged = {},
            onSearchChanged = {},
            navController = navController
        )
    }
}
