package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animeapp.ui.searchview.SearchScreenContent
import com.example.domain.search.model.AnimeModel
import kotlinx.coroutines.flow.flowOf
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimeSearchScreenTest {

    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    fun testAnimeScreenContent(){

        val animePaginated = flowOf(
            PagingData.from(
                listOf(
                    AnimeModel(
                        id = 1,
                        name = "Demon Slayer",
                        image = ""
                    ),
                    AnimeModel(
                        id = 1,
                        name = "Demon Slayer",
                        image = ""
                    )
                )
            )
        )

        composeTestRule.setContent {
            val animeListPaginated = animePaginated.collectAsLazyPagingItems()
            SearchScreenContent(
                animeList = animeListPaginated,
                onFavoriteSelected = {},
                animeFav = emptySet(),
                onTypeChanged = {},
                onSortChanged = {},
                onSearchChanged = {},
                onAnimeSelected = {}
            )
        }
    }
}