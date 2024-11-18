package com.aj.whatmovie.domain.use_cases

import com.aj.whatmovie.data.models.APIResponse
import com.aj.whatmovie.data.models.MovieListAPIResponse
import com.aj.whatmovie.domain.repository.DiscoverMoviesRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val moviesRepository: DiscoverMoviesRepository
) {
    operator fun invoke(selectedGenres: List<Int>?=null, page: Int) = flow<APIResponse<MovieListAPIResponse>> {
        try {
            emit(APIResponse.Loading())
            val response = moviesRepository.getMoviesByGenre(
                genres = selectedGenres?.joinToString(separator = ","),
                page = page
            )

            if (response.total_results!= 0 && !response.results.isNullOrEmpty())
                emit(APIResponse.Success(response))
            else
                emit(APIResponse.Error(null, "Something Went Wrong"))
        } catch (e: HttpException) {
            Timber.e("GetMovieGenres", e.message)
            emit(APIResponse.Error(null, "Something Went Wrong"))
        } catch (e: Exception) {
            Timber.e("GetMovieGenres", e.message)
            emit(APIResponse.Error(null, "Something Went Wrong"))
        }
    }
}