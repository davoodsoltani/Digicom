package com.hads.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.hads.digikala.data.model.home.Slider
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.ui.theme.localShape
import com.hads.digikala.ui.theme.localSpacing
import com.hads.digikala.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun TopSlider(viewModel: HomeViewModel = hiltViewModel()) {

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val sliderResult by viewModel.slider.collectAsState()
    when (sliderResult) {
        is NetworkResult.Success -> {
            sliderList = sliderResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("error", "top slider error: ${sliderResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(
                    horizontal = localSpacing.current.extraSmall,
                    vertical = localSpacing.current.small
                )
        ) {
            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }
            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(localSpacing.current.medium),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { index ->
                imageUrl = sliderList[index].image
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .apply(
                                block = fun ImageRequest.Builder.() {
                                    scale(Scale.FILL)
                                }
                            )
                            .build()
                    )

                    Image(
                        painter = painter, contentDescription = "", modifier =
                        Modifier
                            .padding(
                                localSpacing.current.small
                            )
                            .clip(localShape.current.medium)
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(localSpacing.current.samiLarge),
                        activeColor = Color.Black,
                        inactiveColor = Color.LightGray,
                        indicatorWidth = localSpacing.current.small,
                        indicatorHeight = localSpacing.current.small,
                        indicatorShape = CircleShape
                    )

                }
            }

            LaunchedEffect(key1 = pagerState.currentPage){
                delay(6000)
                var nextPosition = pagerState.currentPage + 1
                if (nextPosition > sliderList.size -1) nextPosition = 0
                pagerState.scrollToPage(nextPosition)
            }
        }
    }
}