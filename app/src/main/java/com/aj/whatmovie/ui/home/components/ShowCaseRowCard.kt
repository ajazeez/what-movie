package com.aj.whatmovie.ui.home.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.aj.whatmovie.R
import com.aj.whatmovie.data.models.MovieData
import com.aj.whatmovie.ui.theme.LocalCustomColorPalette
import com.aj.whatmovie.ui.theme.ImageBackgroundColor
import com.aj.whatmovie.utils.AppConstants

@Composable
fun ShowCaseRowCard(
    movieData: MovieData,
    modifier: Modifier,
    index: Int
) {
    ConstraintLayout(
        modifier = modifier
            .padding(horizontal = 4.dp)

    ) {
        val (
            imgPosterRef,
            txtNumberRef,
            txtTitleRef,
            txtDescRef
        ) = createRefs()
        Card(
            modifier = Modifier.constrainAs(imgPosterRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        },
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = ImageBackgroundColor
            )

        ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f/4f),
            model = "${AppConstants.w500}/${movieData.posterPath}",
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(R.drawable.ic_placeholder),
            clipToBounds = true,
            contentScale = ContentScale.FillWidth,
            contentDescription = movieData.title
        )
            }

        Text(
            modifier = Modifier.constrainAs(txtNumberRef) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            text = "",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            modifier = Modifier.constrainAs(txtTitleRef) {
                start.linkTo(parent.start)
                top.linkTo(imgPosterRef.bottom)
            },
            text = "${movieData.title}(${movieData.releaseDate?.substring(0,4)?:"N/A"})",
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            color = LocalCustomColorPalette.current.textColor
        )
    }
}