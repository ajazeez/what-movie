package com.aj.whatmovie.domain.use_cases

import com.aj.whatmovie.data.models.APIResponse
import com.aj.whatmovie.domain.repository.MovieGenreRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(
    private val movieGenreRepository: MovieGenreRepository
) {

    operator fun invoke() = flow {
        try {
            emit(APIResponse.Loading())
            val response = movieGenreRepository.getMovieGenres()

            Timber.e("GetMovieGenres", "Response $response")

            if (!response.genres.isNullOrEmpty())
                emit(APIResponse.Success(response.genres))
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