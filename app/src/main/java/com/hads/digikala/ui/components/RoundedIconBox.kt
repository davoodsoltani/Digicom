package com.hads.digikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hads.digikala.ui.theme.darkText
import com.hads.digikala.ui.theme.localShape
import com.hads.digikala.ui.theme.localSpacing

@Composable
fun RoundedIconBox(
    title: String,
    image: Painter,
    bgColor: Color = Color.Transparent,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(80.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .clip(localShape.current.biggerMedium)
                .background(bgColor),
        ) {
            Image(
                modifier = Modifier.size(52.dp),
                painter = image,
                contentDescription = "",
            )
        }
        Spacer(modifier = Modifier.height(localSpacing.current.small))

        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

    }
}