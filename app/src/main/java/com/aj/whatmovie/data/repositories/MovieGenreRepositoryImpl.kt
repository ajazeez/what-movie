package com.aj.whatmovie.data.repositories

import com.aj.whatmovie.data.models.MovieGenreResponse
import com.aj.whatmovie.data.services.MoviesAPI
import com.aj.whatmovie.domain.repository.MovieGenreRepository
import javax.inject.Inject

class MovieGenreRepositoryImpl @Inject constructor(
    private val moviesAPI: MoviesAPI
): MovieGenreRepository{

    override suspend fun getMovieGenres(): MovieGenreResponse =
        moviesAPI.getMovieGenres()
}