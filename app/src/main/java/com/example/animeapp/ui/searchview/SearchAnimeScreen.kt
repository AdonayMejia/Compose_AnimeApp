package com.example.animeapp.ui.searchview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.ui.searchview.component.SearchBar
import com.example.animeapp.ui.searchview.viewmodel.SearchViewModel
import com.example.domain.search.model.AnimeModel

@Composable
fun SearchAnimeScreen(
    viewModel: SearchViewModel
//    onSelectAnime:(Int) -> Unit,
//    onDismissAnime:() -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val anime = viewModel.animeFlow.collectAsLazyPagingItems()
    SearchScreenContent(
        isLoading = uiState.isLoading,
        animeList = anime,
        onSelectedAnime = uiState.onSelectAnime
    )
}

@Composable
fun SearchScreenContent(
    isLoading:Boolean,
    animeList:LazyPagingItems<AnimeModel>,
    onSelectedAnime:(Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 80.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }else{
            LazyVerticalGrid(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize(),
            ){
                items(animeList.itemCount){anime ->
                    val animes = animeList[anime]
//                    AnimeItem(
//                        anime = animes,
//                        modifier = Modifier.fillMaxSize(),
//                        onSelectedAnime = {onSelectedAnime(anime.id)}
//                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPrev() {
    val viewModel: SearchViewModel = hiltViewModel()
    SearchAnimeScreen(viewModel = viewModel)
}
