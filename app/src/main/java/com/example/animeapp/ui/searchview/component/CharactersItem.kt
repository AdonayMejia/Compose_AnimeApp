package com.example.animeapp.ui.searchview.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.domain.details.model.Character

@Composable
fun CharacterItem(characters: List<Character>, onCharacterClick: (Int) -> Unit) {
    LazyRow {
        items(characters.size) { index ->
            val character = characters[index]
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(120.dp)
                    .height(150.dp)
                    .clickable { onCharacterClick(character.id) },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (LocalInspectionMode.current) {
                        Image(
                            painter = painterResource(R.drawable.naruto),
                            contentDescription = "image",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(character.image),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterItem() {
    val characters = listOf(
        Character(
            id = 1,
            name = "Rengoku",
            image = "https://www.geekmi.news/__export/1641913206480/sites/debate/img/2022/01/11/rengoku_x2x.jpg_554688468.jpg",
            description = null
        ),
        Character(
            id = 2,
            name = "Tanjiro",
            image = "https://wave.fr/images/1916/05/demon-slayer-nouveau-manga-phenomene-1.jpg",
            description = null
        ),
        Character(
            id = 3,
            name = "Kanroji",
            image = "https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2023/04/kimetsu-no-yaiba-husbando-mitsuri-fanart.jpg",
            description = null
        )
    )
    AnimeAppTheme {
        CharacterItem(characters = characters, onCharacterClick = { /* Do something */ })
    }
}
