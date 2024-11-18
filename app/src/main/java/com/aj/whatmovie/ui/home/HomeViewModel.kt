package com.aj.whatmovie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingData.Companion.empty
import androidx.paging.cachedIn
import com.aj.whatmovie.data.data_sources.MoviesByGenreSource
import com.aj.whatmovie.data.data_sources.PopularMovieSource
import com.aj.whatmovie.data.models.MovieData
import com.aj.whatmovie.data.models.MovieGenre
import com.aj.whatmovie.domain.repository.DiscoverMoviesRepository
import com.aj.whatmovie.domain.use_cases.GetGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularMovieSource: PopularMovieSource,
    private val moviesRepository: DiscoverMoviesRepository,
    private val getGenreUseCase: GetGenreUseCase
) : ViewModel() {

    private val _popularMoviesData: MutableStateFlow<PagingData<MovieData>> =
        MutableStateFlow(empty())
    private val _topMoviesData: MutableStateFlow<MutableMap<MovieGenre?, PagingData<MovieData>>> =
        MutableStateFlow(
            mutableMapOf()
        )

    private val _genreData: MutableStateFlow<List<MovieGenre>> = MutableStateFlow(emptyList())

    var popularMoviesData = _popularMoviesData.asStateFlow()
        private set


    private val _genreMovieData: Flow<List<GenreMoviesState>> = _genreData.map { genres ->
        genres.map { genre ->
            val genreMovies = getMoviesByGenre(genre)
            GenreMoviesState(genre = genre, movies = genreMovies)
        }
    }

    var genreMovieData = _genreMovieData
        private set

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            getGenreUseCase()
                .catch { e -> Timber.e("Genre Response Exception", e.message) }
                .collect({ value ->
                    value.data?.let {
                        Timber.d("Genre Response ", it)
                        _genreData.value = it
                    }
                })
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = 5,
                    enablePlaceholders = true
                ),
            ) {
                popularMovieSource
            }.flow.cachedIn(viewModelScope).collect {
                _popularMoviesData.value = it
            }
        }
    }

    private fun getMoviesByGenre(genre: MovieGenre) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = true
            )
        ) {
            MoviesByGenreSource(genre = genre, repository = moviesRepository)
        }.flow.cachedIn(viewModelScope)
}