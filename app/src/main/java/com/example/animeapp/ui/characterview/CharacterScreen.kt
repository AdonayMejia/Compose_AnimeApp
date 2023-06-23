package com.example.animeapp.ui.characterview

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.model.navigation.BottomBarScreens
import com.example.animeapp.model.navigation.components.AppBar
import com.example.animeapp.ui.characterview.viewmodel.CharacterViewModel
import com.example.animeapp.ui.detailview.utils.ChangeDescriptionFormat
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.domain.details.model.Character

@Composable
fun CharacterScreen(
    characterId: Int,
    navController: NavHostController,
    characterViewModel: CharacterViewModel = hiltViewModel()

) {
    val characterDetails by characterViewModel.characterDetails.collectAsState()

    characterDetails?.let { character ->
        Scaffold(
            topBar = {
                AppBar {
                    navController.navigate(BottomBarScreens.FavoriteScreen.route)
                }
            }
        ) { padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                CharacterScreenContent(characterDetails = character)
            }
        }
    } ?: run {
        characterViewModel.fetchCharacterDetails(characterId)
        Log.wtf("Personaje","Id: $characterId")
    }
}

@Composable
private fun CharacterScreenContent(characterDetails: Character) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp
            ),
    ) {
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = painterResource(R.drawable.naruto),
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Fit
                    )

                } else {
                    Image(
                        painter = rememberAsyncImagePainter(model = characterDetails.image),
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
        item {
            ChangeDescriptionFormat(description = if (characterDetails.description?.isNotEmpty() == true) characterDetails.description else "There is no description for this character")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCharacterScreen() {
    val character = Character(
        id = 1,
        image = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
        name = "Naruto",
        description = stringResource(R.string.demon_slayer_description)
    )
    AnimeAppTheme {
        Box {

            CharacterScreenContent(characterDetails = character)
        }
    }
}