package com.aj.whatmovie.domain.repository

import com.aj.whatmovie.data.models.MovieGenreResponse

interface MovieGenreRepository {
    suspend fun getMovieGenres(): MovieGenreResponse
}