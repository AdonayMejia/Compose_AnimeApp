package com.example.animeapp.ui.searchview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeapp.R
import com.example.domain.search.model.AnimeModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { newValue ->
            onSearchChanged(newValue)
        },
        label = { Text(text = "Search Anime") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            scope.launch {
                onSearchChanged(searchQuery)
                keyBoardController?.hide()
            }
        }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
        ),
        trailingIcon = {
            IconButton(onClick = {
                scope.launch {
                    onSearchChanged(searchQuery)
                    keyBoardController?.hide()
                }
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
        Box(modifier = Modifier.background(Color.Black)) {
            SearchBar(
                searchQuery = "Naruto",
                onSearchChanged = {}
            )
        }
}