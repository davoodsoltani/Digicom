package com.hads.digicom.ui.screens.product_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hads.digicom.data.model.product_detail.ProductColor
import com.hads.digicom.ui.theme.darkText
import com.hads.digicom.ui.theme.spacing

@Composable
fun ProductSelectColorSection(
    colors: List<ProductColor>
) {

    Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {

        Text(
            text = "رنگ : انتخاب نشده",
            color = MaterialTheme.colors.darkText,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )


        LazyRow() {
            items(colors) { productColor ->
                ColorChipItem(productColor)
            }
        }
    }

}