package com.hads.digicom.ui.screens.product_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hads.digicom.data.model.product_detail.Comment
import com.hads.digicom.ui.theme.spacing
import com.hads.digicom.R
import com.hads.digicom.ui.theme.LightCyan
import com.hads.digicom.ui.theme.darkText

@Composable
fun ProductCommentsSection(
    comments: List<Comment>,
    commentCount: Int
) {


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.samiLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.user_comments),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = "$commentCount " + stringResource(R.string.comment),
                color = MaterialTheme.colors.LightCyan,
                style = MaterialTheme.typography.h4,
            )
        }
    }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
    ){
        items(comments){ comment ->
            TextCommentCard(comment)
        }
    }

}