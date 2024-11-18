package com.aj.whatmovie.ui.home

import androidx.paging.PagingData
import com.aj.whatmovie.data.models.MovieData
import com.aj.whatmovie.data.models.MovieGenre
import kotlinx.coroutines.flow.Flow

data class GenreMoviesState(
    val genre: MovieGenre,
    val movies: Flow<PagingData<MovieData>>
)
