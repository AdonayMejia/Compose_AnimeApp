package com.example.animeapp.ui.detailview.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jsoup.Jsoup

@Composable
fun ChangeDescriptionFormat(description: String?) {
    description?.let {
        val cleanDescription = Jsoup.parse(description).text()
        Text(
            text = cleanDescription,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}