package com.aj.whatmovie.ui.home.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.aj.whatmovie.ui.home.HomeViewModel

@Composable
fun BannerCarouselRow(modifier: Modifier) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    LaunchedEffect("FeaturedCarousel") {
        homeViewModel.getPopularMovies()
    }

    val popularMovies = homeViewModel.popularMoviesData.collectAsLazyPagingItems()
    val carouselRowState = rememberLazyListState()
    LazyRow(
        state = carouselRowState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = carouselRowState)
    ) {
        items(popularMovies.itemCount) {
            popularMovies[it]?.let { movieData ->
                FeaturedMovieCard(
                    modifier = modifier,
                    movieData = movieData
                )
            }
        }
    }
}