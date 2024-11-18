package com.aj.whatmovie.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.aj.whatmovie.data.models.MovieData
import com.aj.whatmovie.data.models.MovieGenre
import com.aj.whatmovie.ui.theme.LocalCustomColorPalette
import kotlinx.coroutines.flow.Flow

@Composable
fun ShowcaseRow(
    modifier: Modifier,
    movieGenre: MovieGenre,
    moviesData: Flow<PagingData<MovieData>>
) {
    val moviesList = moviesData.collectAsLazyPagingItems()

    Column {
        Text(
            text = movieGenre.name ?: "",
            fontWeight = FontWeight.SemiBold,
            color = LocalCustomColorPalette.current.textColor,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = moviesList.itemCount,
                contentType = moviesList.itemContentType { "showcase" }

            ) { index ->
                moviesList[index]?.let {
                    ShowCaseRowCard(
                        modifier = Modifier.fillParentMaxWidth(.3f),
                        movieData = it,
                        index = index
                    )
                }
            }
            if(moviesList.loadState.append is LoadState.Loading) {
                item{ CircularProgressIndicator()}
            }
        }
    }
}