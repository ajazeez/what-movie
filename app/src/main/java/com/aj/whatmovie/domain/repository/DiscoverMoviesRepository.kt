package com.aj.whatmovie.domain.repository

import com.aj.whatmovie.data.models.MovieListAPIResponse

interface DiscoverMoviesRepository {
    suspend fun getMoviesByGenre(genres: String? = null, page: Int): MovieListAPIResponse
    suspend fun getPopularMovies(page: Int): MovieListAPIResponse
}