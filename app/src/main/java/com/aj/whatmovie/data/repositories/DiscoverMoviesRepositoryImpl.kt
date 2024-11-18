package com.aj.whatmovie.data.repositories

import com.aj.whatmovie.data.models.SortOptions
import com.aj.whatmovie.data.services.MoviesAPI
import com.aj.whatmovie.domain.repository.DiscoverMoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiscoverMoviesRepositoryImpl @Inject constructor(
    private val moviesAPI: MoviesAPI
): DiscoverMoviesRepository{

    override suspend fun getMoviesByGenre(genres: String?, page: Int) = moviesAPI.getDiscoverMovie(
        genreIds = genres,
        page = page
    )

    override suspend fun getPopularMovies(page: Int) = moviesAPI.getDiscoverMovie(
        sortBy = SortOptions.POPULARITY_DESC.value,
        page = page
    )
}