package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animeapp.ui.searchview.component.SearchBar
import com.example.animeapp.ui.theme.AnimeAppTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testSearchBar() {
        var search = ""
        val textQuery = "Demon Slayer"
        composeTestRule.setContent {
            AnimeAppTheme {
                SearchBar(
                    searchQuery = search ,
                    onSearchChanged = { input ->
                        search = input
                    }
                )
            }
        }
        composeTestRule.onNodeWithText(text = "Search Anime").performTextInput(textQuery)
        assertEquals(textQuery,search)
    }

//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.animeapp", appContext.packageName)
//    }
}