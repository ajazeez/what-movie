package com.aj.whatmovie.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aj.whatmovie.data.models.MovieData
import com.aj.whatmovie.data.models.MovieGenre
import com.aj.whatmovie.domain.repository.DiscoverMoviesRepository
import timber.log.Timber

class MoviesByGenreSource(
    private val genre: MovieGenre,
    private val repository: DiscoverMoviesRepository
): PagingSource<Int, MovieData>() {

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        val page = params.key ?: 1
        Timber.tag("MoviesByGenreSource").d("Page $page Genre $genre")
        return try {
            val response = repository.getMoviesByGenre(genres = genre.id, page = page)
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