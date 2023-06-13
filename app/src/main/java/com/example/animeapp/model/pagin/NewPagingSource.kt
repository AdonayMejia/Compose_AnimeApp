package com.example.animeapp.model.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import com.example.domain.usecases.GetAnimeUseCase
import javax.inject.Inject

class NewPagingSource(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val search:String? = null,
    private val sort:List<AnimeSort>,
    private val type: AnimeType
) : PagingSource<Int, AnimeModel>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeModel>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeModel> {
        return try {
            val page = params.key ?: 1
            val response = getAnimeUseCase.getExecute(
                page = page,
                perPage = 10,
                search = search,
                sort = sort,
                type = type
            )
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}