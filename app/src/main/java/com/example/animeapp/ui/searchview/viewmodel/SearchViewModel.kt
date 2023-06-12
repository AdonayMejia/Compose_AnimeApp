package com.example.animeapp.ui.searchview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animeapp.model.pagin.NewPagingSource
import com.example.animeapp.ui.favoriteview.uistate.UiState
import com.example.animeapp.ui.searchview.uistate.SearchUiState
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.usecases.favorites.AddFavoriteAnimeUseCase
import com.example.domain.usecases.favorites.DeleteFavAnimeUseCase
import com.example.domain.usecases.favorites.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val addAnimeToFavoriteUseCase: AddFavoriteAnimeUseCase,
    private val removeAnimeUseCase: DeleteFavAnimeUseCase,
    private val updateFavAnime: UpdateFavoriteUseCase
) : ViewModel(){

    private val _search = MutableStateFlow<String?>(null)
    private val _type = MutableStateFlow(AnimeType.ANIME)
    private val _sort = MutableStateFlow<List<AnimeSort>>(emptyList())

    private val _favUiState = MutableStateFlow(
        UiState(
            favAnime = emptySet()
        )
    )

    val favUiState = _favUiState.asStateFlow()

private val _searchUiState = MutableStateFlow(
    SearchUiState(
        isLoading = false,
        addToFavorites = this::addAnimeToFavorite,
        favoriteAnime = emptySet(),
        onTypeChanged = this::onTypeChanged,
        onSortChanged = this::onSortChanged,
        onSearchChanged = this::onSearchChanged
    )
)
    val uiState = _searchUiState.asStateFlow()
    init {
        viewModelScope.launch{
            updateFavAnime().
                    collect{ updateAnime ->
                        _favUiState.value = _favUiState.value.copy(favAnime = updateAnime)
                    }
        }
    }

    private fun createPaging(
        type:AnimeType,
        sort:List<AnimeSort>,
        search:String?
    ): Pager<Int,AnimeModel>{
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewPagingSource(getAnimeUseCase,search,sort,type) }
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val animeFlow: Flow<PagingData<AnimeModel>> = combine(_type, _sort, _search) { type, sort, search ->
        Triple(type,sort, search)
    }.flatMapLatest {(type,sort, search) ->
        createPaging(type,sort,search).flow
    }.cachedIn(viewModelScope)

    private fun addAnimeToFavorite(anime:AnimeModel){
        viewModelScope.launch {
            if (_favUiState.value.favAnime.contains(anime.id)){
                removeAnimeUseCase(anime.id)
            } else {
                addAnimeToFavoriteUseCase(anime)
            }
        }
    }
    private fun onTypeChanged(type: AnimeType) {
        _type.value = type
    }

    private fun onSortChanged(sort: AnimeSort) {
        _sort.value = listOf(sort)
    }

    private fun onSearchChanged(query: String) {
        _search.value = query
    }

    companion object{
        private const val PAGE_SIZE = 10
    }

}