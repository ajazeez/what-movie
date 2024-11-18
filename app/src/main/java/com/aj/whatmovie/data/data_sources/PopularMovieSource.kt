package com.aj.whatmovie.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aj.whatmovie.data.models.MovieData
import com.aj.whatmovie.domain.repository.DiscoverMoviesRepository
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

class PopularMovieSource @Inject constructor(
    private val repository: DiscoverMoviesRepository
): PagingSource<Int, MovieData>() {
    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? = state.anchorPosition


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        val page = params.key ?: 1
        Timber.tag("PopularMovieSource").d("Page $page")

        return try {
            val response = repository.getPopularMovies(page = page)
            LoadResult.Page(
                data = response.results?: emptyList(),
                prevKey = if(page == 1)null else page.minus(1),
                nextKey = if(response.total_pages!=null && page<response.total_pages) page.plus(1) else null
            )
        }catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}