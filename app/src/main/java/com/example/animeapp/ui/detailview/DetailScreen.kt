package com.example.animeapp.ui.detailview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.domain.details.model.AnimeDetails

@Composable
fun DetailScreen(
    id: Int,
    navController:NavHostController
) {

}

@Composable
fun DetailScreenContent(
    animeDetails:AnimeDetails
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val painter = rememberAsyncImagePainter(model = animeDetails.banner)
        if (LocalInspectionMode.current){
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Banner",
                modifier = Modifier
                    .fillMaxWidth(),
                alpha = 0.8f
                )
        } else{
            Image(
                painter = painter,
                contentDescription = "Banner",
                modifier = Modifier
                    .fillMaxWidth(),
                alpha = 0.8f
            )
        }
        Text(
            text = animeDetails.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${animeDetails.episodes} episodes - ${animeDetails.genre} - ${animeDetails.score}"
        )
        Text(text = animeDetails.description.orEmpty())
    }
}