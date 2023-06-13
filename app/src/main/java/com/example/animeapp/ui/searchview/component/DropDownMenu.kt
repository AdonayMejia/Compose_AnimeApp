package com.example.animeapp.ui.searchview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilterDropDown(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
) {
    val filterMenuExpanded = remember { mutableStateOf(false) }
    val text = rememberSaveable { mutableStateOf(selectedItem) }

    Box {
        Row(
            modifier = Modifier
                .clickable(onClick = { filterMenuExpanded.value = true })
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                .padding(
                    horizontal = (8.dp),
                    vertical = (4.dp),
                )
                .wrapContentSize()
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = text.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
            DropdownMenu(
                expanded = filterMenuExpanded.value,
                onDismissRequest = { filterMenuExpanded.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            text.value = it
                            onItemSelected(it)
                            filterMenuExpanded.value = false
                        }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun FiltersPreview() {
    val items = listOf("Item 1")
    val selectedItem by remember { mutableStateOf(items.first()) }
    FilterDropDown(
        items = items,
        selectedItem = selectedItem,
        onItemSelected = { }
    )
}
