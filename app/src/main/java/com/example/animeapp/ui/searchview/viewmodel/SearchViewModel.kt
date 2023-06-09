package com.example.animeapp.ui.searchview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animeapp.model.pagin.NewPagingSource
import com.example.animeapp.ui.searchview.uistate.SearchUiState
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.usecases.GetCharactersUseCase
import com.example.domain.usecases.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel(){

    private val _search = MutableStateFlow<String?>("")
    private val _type = MutableStateFlow(AnimeType.ANIME)
    private val _sort = MutableStateFlow<List<AnimeSort>>(listOf())

private val _searchUiState = MutableStateFlow(
    SearchUiState(
        isLoading = false,
        selectedAnime = null,
        anime = emptyList(),
        onSelectAnime = ::selectedAnime
    )
)
    val uiState = _searchUiState.asStateFlow()
    init {
        viewModelScope.launch{
            _searchUiState.update { it.copy(
                isLoading = true
            ) }
//            _searchUiState.update { it.copy(
//                anime = getAnimeUseCase.getExecute(),
//                isLoading = false
//            ) }
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

     private fun selectedAnime(id:Int){
        viewModelScope.launch {
            _searchUiState.update { it.copy(
                selectedAnime = getDetailsUseCase.getExecute(id)
            ) }
        }
    }

//    private fun dismissAnimeDialog(){
//        viewModelScope.launch {
//            _searchUiState.update { it.copy(
//                selectedAnime = null
//            ) }
//        }
//    }

    companion object{
        private const val PAGE_SIZE = 10
    }

}