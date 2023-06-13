package com.example.animeapp.ui.detailview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.model.navigation.BottomBarScreens
import com.example.animeapp.model.navigation.components.AppBar
import com.example.animeapp.ui.detailview.utils.ChangeDescriptionFormat
import com.example.animeapp.ui.detailview.viewmodel.DetailViewModel
import com.example.animeapp.ui.searchview.component.CharacterItem
import com.example.domain.details.model.AnimeDetails
import org.jsoup.Jsoup

@Composable
fun DetailScreen(
    id: Int,
    navController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val animeDetails by detailViewModel.animeDetails.collectAsState(null)
    LaunchedEffect(key1 = id) {
        detailViewModel.fetchAnimeDetails(id)
    }

    animeDetails?.let { details ->
        Scaffold(
            topBar = {
                AppBar {
                    navController.navigate(BottomBarScreens.FavoriteScreen.route)
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailScreenContent(animeDetails = details, onCharacterClick = { characterId ->
                    navController.navigate("CharacterScreen/$characterId")
                })
            }

        }
    }

}

@Composable
fun DetailScreenContent(
    animeDetails:AnimeDetails,
    onCharacterClick: (Int) -> Unit

) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box {
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(R.drawable.banner),
                    contentDescription = "Detail Image",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillHeight
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(animeDetails.banner),
                    contentDescription = "Detail Image",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = animeDetails.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Genres - ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 5.dp)
                )
                animeDetails.genre?.take(3)?.forEach { genre ->
                    Text(
                        text = "$genre,",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            ChangeDescriptionFormat(animeDetails.description)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text ="Episodes - ${animeDetails.episodes ?: 1}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "Score - ${animeDetails.score ?: 1}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (animeDetails.englishTitle.isEmpty()) "English name - There is no english name on this anime" else "English name - ${animeDetails.englishTitle}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                text = "Native name - ${animeDetails.nativeTitle}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

        }
        CharacterItem(
            characters = animeDetails.character ?: emptyList(),
            onCharacterClick = onCharacterClick
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
}
