package com.example.animeapp.ui.searchview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.ui.searchview.component.AnimeItem
import com.example.animeapp.ui.searchview.uistate.SearchUiState
import com.example.animeapp.ui.searchview.viewmodel.SearchViewModel
import com.example.domain.search.model.AnimeModel
import kotlinx.coroutines.runBlocking

@Composable
fun SearchAnimeScreen(
    state:SearchUiState,
    onSelectAnime:(Int) -> Unit,
    onDismissAnime:() -> Unit
) {

    var searchQuery by remember { mutableStateOf("") }
    var movies by remember { mutableStateOf<List<AnimeModel>>(emptyList()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { textValue ->
                searchQuery = textValue
            },
            label = {
                Text(text = "Search Movies")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { //Call the API when the search button is clicked

            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Search")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (state.isLoading){
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
                items(state.anime){anime ->
                    AnimeItem(
                        anime = anime,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreenContent() {

}
