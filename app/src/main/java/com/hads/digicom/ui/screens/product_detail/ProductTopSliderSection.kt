package com.hads.digicom.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.hads.digicom.data.model.product_detail.SliderImage
import com.hads.digicom.ui.theme.localShape
import com.hads.digicom.ui.theme.localSpacing
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductTopSliderSection(
    sliderList: List<SliderImage>
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }

            Box() {

                HorizontalPager(
                    count = sliderList.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { index ->
                    imageUrl = sliderList[index].image
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = imageUrl)
                                .apply(
                                    block = fun ImageRequest.Builder.() {
                                        scale(Scale.FILL)
                                    }
                                )
                                .build()
                        )
                        Image(
                            painter = painter, contentDescription = "", modifier = Modifier
                                .padding(localSpacing.current.small)
                                .clip(localShape.current.medium)
                                .fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )

                    }
                }

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


            LaunchedEffect(key1 = pagerState.currentPage) {
                delay(6000)
                var newPosition = pagerState.currentPage + 1
                if (newPosition > sliderList.size - 1) newPosition = 0
                pagerState.scrollToPage(newPosition)
            }


        }
    }

}