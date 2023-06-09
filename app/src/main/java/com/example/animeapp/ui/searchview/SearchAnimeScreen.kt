package com.example.animeapp.ui.searchview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.paging.compose.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.ui.searchview.component.AnimeItem
import com.example.animeapp.ui.searchview.component.SearchBar
import com.example.animeapp.ui.searchview.uistate.SearchUiState
import com.example.animeapp.ui.searchview.viewmodel.SearchViewModel
import com.example.data.apolloextensions.toOptional
import com.example.domain.search.model.AnimeModel
import kotlinx.coroutines.runBlocking

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
