package com.example.animeapp.ui.searchview.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeapp.R
import com.example.animeapp.ui.searchview.utils.SortAnime
import com.example.animeapp.ui.searchview.utils.synchronizeName
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType

@Composable
fun OptionsMenu(
    animeType: AnimeType,
    animeSort: AnimeSort,
    animeTypeSelected: (AnimeType) -> Unit,
    animeSortSelected: (AnimeSort) -> Unit
) {
    val type = AnimeType.values().map { it.name }
    val sort = AnimeSort.values().map { SortAnime.sortDisplayName[it] ?: it.name }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        FilterDropDown(
            items = type,
            selectedItem = animeType.name,
            onItemSelected = {
                animeTypeSelected(AnimeType.valueOf(it))
            }
        )
        FilterDropDown(
            items = sort,
            selectedItem = SortAnime.sortDisplayName[animeSort] ?: animeSort.name,
            onItemSelected = {
                synchronizeName(
                    it, SortAnime.sortDisplayName
                )?.let{
                    animeSortSelected(it)
                }
            }
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Preview(showBackground = true)
@Composable
fun FilterPReview() {
    val selectedType = AnimeType.ANIME
    val selectedSort = AnimeSort.SCORE

        OptionsMenu(
            animeType = selectedType,
            animeSort = selectedSort,
            animeTypeSelected = {/*Handle type selection*/ },
            animeSortSelected = { /* Handle sort*/ }
        )
}