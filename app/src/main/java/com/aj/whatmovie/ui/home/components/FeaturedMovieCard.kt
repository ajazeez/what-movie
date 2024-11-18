package com.aj.whatmovie.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aj.whatmovie.R
import com.aj.whatmovie.data.models.MovieData
import com.aj.whatmovie.ui.theme.ImageBackgroundColor
import com.aj.whatmovie.utils.AppConstants

@Composable
fun FeaturedMovieCard(
    modifier: Modifier, movieData: MovieData
) {
    Card (
        modifier = modifier
            .aspectRatio(3f/4f),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        AsyncImage(
            model = "${AppConstants.originalSize}/${movieData.posterPath}",
            modifier = Modifier
                .fillMaxWidth()
                .background(ImageBackgroundColor),
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(R.drawable.ic_placeholder),
            clipToBounds = true,
            contentScale = ContentScale.FillWidth,
            contentDescription = movieData.title
        )
    }
}