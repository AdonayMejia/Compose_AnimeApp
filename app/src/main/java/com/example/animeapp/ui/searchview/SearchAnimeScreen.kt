package com.example.animeapp.ui.searchview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.ui.searchview.uistate.SearchUiState
import com.example.animeapp.ui.searchview.viewmodel.SearchViewModel
import com.example.domain.search.model.AnimeModel

@Composable
fun SearchAnimeScreen(
    state:SearchUiState,
    onSelectAnime:(Int) -> Unit,
    onDismissAnime:() -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()){
        if (state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }else{
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.anime){anime ->
                    AnimeItem(
                        anime = anime,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { onSelectAnime(anime.id) }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeItem(
    anime:AnimeModel,
    modifier: Modifier
) {
    val painter = rememberAsyncImagePainter(model = anime.image)
    Column {
        Image(modifier = modifier.size(width = 90.dp, height = 90.dp),painter = painter, contentDescription = "Image" )
        Spacer(modifier = modifier.padding(16.dp))
        Text(text = anime.name)
    }
}