package com.aj.whatmovie.data.services

import com.aj.whatmovie.data.models.MovieGenreResponse
import com.aj.whatmovie.data.models.MovieListAPIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): MovieGenreResponse

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("include_adult")includeAdultContent: Boolean = false,
        @Query("include_video")includeVideo: Boolean = false,
        @Query("with_genres", encoded = true)genreIds: String?=null,
        @Query("with_keywords")keywords: String?=null,
        @Query("sort_by")sortBy: String?=null,
        @Query("page")page: Int = 1
    ): MovieListAPIResponse

}