package com.example.animeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.animeapp.ui.characterview.viewmodel.CharacterViewModel
import com.example.domain.details.model.Character
import com.example.domain.usecases.GetCharactersUseCase
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
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class CharacterViewModelTest {

    @RelaxedMockK
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private lateinit var characterViewModel: CharacterViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        characterViewModel = CharacterViewModel((getCharactersUseCase))
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun testCharacterVm() = runTest {
        //Given
        val characterList = MutableStateFlow(
            Character(
            id = 1,
            name = "Naruto",
            image = "",
            description = ""
        )
        )
        val id = 1
        coEvery { getCharactersUseCase(id) } returns characterList.value

        //When
        characterViewModel.fetchCharacterDetails(id)

        //Then
        assert(characterViewModel.characterDetails.value == characterList.value)
    }

}