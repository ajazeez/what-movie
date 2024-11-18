package com.aj.whatmovie.data.models

data class MovieListAPIResponse(
    val page: Int? = null,
    val results: List<MovieData>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)
