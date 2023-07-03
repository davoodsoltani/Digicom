package com.hads.digicom.ui.screens.product_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hads.digicom.R
import com.hads.digicom.data.model.product_detail.ProductDetail
import com.hads.digicom.ui.theme.*
import com.hads.digicom.utils.DigitHelper.digitByLocate

@Composable
fun ProductDetailHeaderSection(
    item: ProductDetail
) {


    Column {
        Text(
            text = "${stringResource(id = R.string.category)} / ${item.category}",
            color = MaterialTheme.colors.DarkCyan,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )

        Text(
            text = item.name.toString(),
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
            maxLines = 2
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colors.Gold
            )
            Text(
                text = digitByLocate(item.star.toString()),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
            Text(
                text = digitByLocate("(${item.starCount})"),
                color = MaterialTheme.colors.grayAlpha,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )

            Text(
                text = digitByLocate("${item.commentCount} ${stringResource(R.string.user_comments)}"),
                color = MaterialTheme.colors.DarkCyan,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )
            Text(
                text = digitByLocate("${item.viewCount} ${stringResource(R.string.view)}"),
                color = MaterialTheme.colors.DarkCyan,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small,
                )
                .fillMaxWidth()
        ){
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colors.DigicomLightGreen
            )

            Text(
                text = digitByLocate("90% (80نفر) از خریداران این کالا را پیشنهاد کرده اند."),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
        }

        Divider(
            color = MaterialTheme.colors.grayCategory,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium))


    }
}