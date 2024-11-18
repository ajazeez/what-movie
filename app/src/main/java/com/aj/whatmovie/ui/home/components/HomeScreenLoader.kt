package com.aj.whatmovie.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable
fun HomeScreenLoader (){

    DotLottieAnimation(
        source = DotLottieSource.Asset("ic_anim_panda.json"), // from asset .lottie / .json
        autoplay = true,
        loop = true,
        modifier = Modifier.fillMaxSize()
            .scale(1.5f)
            .background(MaterialTheme.colorScheme.background)
    )
}