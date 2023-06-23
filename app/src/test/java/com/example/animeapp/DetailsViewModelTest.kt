package com.example.animeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.res.stringResource
import com.example.animeapp.ui.characterview.viewmodel.CharacterViewModel
import com.example.animeapp.ui.detailview.viewmodel.DetailViewModel
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import com.example.domain.usecases.GetDetailsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class DetailsViewModelTest {

    @RelaxedMockK
    private lateinit var getDetailsUseCase:GetDetailsUseCase

    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        detailViewModel = DetailViewModel(getDetailsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun testDetailVm() = runTest {
        //Given
        val detailList = MutableStateFlow(
            AnimeDetails(
                id = 1,
                title = "Anime Title",
                englishTitle = "English name",
                nativeTitle = "Native name",
                banner = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
                description = "",
                episodes = 24,
                score = 8,
                genre = listOf("Action", "Adventure", "Fantasy"),
                character = listOf(
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
            )
        )
        val id = 1
        coEvery { getDetailsUseCase(id) } returns detailList.value

        //When
        detailViewModel.fetchAnimeDetails(id)

        //Then
        assert(detailViewModel.animeDetails.value == detailList.value)
    }

}