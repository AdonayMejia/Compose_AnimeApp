package com.example.animeapp.ui.searchview.utils

import com.example.domain.search.model.AnimeSort

object SortAnime {
    private const val POPULARITY = "POPULARITY"
    private const val STATUS = "STATUS"
    private const val SCORE = "SCORE"

    val sortDisplayName = mapOf(
        AnimeSort.POPULARITY to POPULARITY,
        AnimeSort.STATUS to STATUS,
        AnimeSort.SCORE to SCORE
    )
}

fun synchronizeName(
    displayName: String,
    sortDisplayName: Map<AnimeSort, String>
): AnimeSort? {
    return sortDisplayName.entries.firstOrNull { it.value == displayName }?.key
}
