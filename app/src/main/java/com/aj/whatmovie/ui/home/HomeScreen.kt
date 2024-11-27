package com.aj.whatmovie.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aj.whatmovie.ui.home.components.BannerCarouselRow
import com.aj.whatmovie.ui.home.components.HomeScreenLoader
import com.aj.whatmovie.ui.home.components.ShowcaseRow

@Composable
fun HomeScreen(modifier: Modifier) {

    val viewModel: HomeViewModel = hiltViewModel()

    val coroutineScope = rememberCoroutineScope()


    val movieGenreData = viewModel.genreMovieData.collectAsState(
        initial = emptyList(),
        context = coroutineScope.coroutineContext
    )

    LaunchedEffect("GetGenres") {
        viewModel.getGenres()
    }
    AnimatedVisibility(
        visible = movieGenreData.value.isEmpty(),
        enter = fadeIn(),
        exit = fadeOut(
            animationSpec = tween(
                delayMillis = 2000,
                durationMillis = 2000
            )
        )
    ) {
        HomeScreenLoader()
    }
    AnimatedVisibility(
        visible = movieGenreData.value.isNotEmpty(),
        enter = fadeIn(
            animationSpec = tween(delayMillis = 2000)
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 200)
        )
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                BannerCarouselRow(
                    modifier = Modifier.fillParentMaxWidth()
                )
            }
            movieGenreData.value.forEach {
                item {
                    ShowcaseRow(
                        modifier = Modifier,
                        movieGenre = it.genre,
                        moviesData = it.movies
                    )
                }
            }
        }
    }
}

