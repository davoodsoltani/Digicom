package com.hads.digicom.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hads.digicom.R
import com.hads.digicom.ui.theme.localElevation
import com.hads.digicom.ui.theme.localShape
import com.hads.digicom.ui.theme.localSpacing
import com.hads.digicom.ui.theme.searchBarBg
import com.hads.digicom.ui.theme.unSelectedBottomBar
import com.hads.digicom.utils.Constants

@Composable
fun SearchBarSection() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        elevation = localElevation.current.extraSmall
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(localSpacing.current.small)
                .clip(localShape.current.biggerSmall)
                .background(MaterialTheme.colors.searchBarBg)
        ) {
            SearchContent()
        }
    }
}

@Composable
private fun SearchContent() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            modifier = Modifier.height(24.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = ""
        )

        Text(
            modifier = Modifier.padding(start = 15.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.unSelectedBottomBar,
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.Normal,
            text = stringResource(id = R.string.search_in)
        )

        Image(
            modifier = Modifier
                .width(80.dp)
                .padding(start = 5.dp, top = 5.dp),
            painter = digicomLogoChangeByLanguage(), contentDescription = ""
        )
    }
}

@Composable
private fun digicomLogoChangeByLanguage(): Painter{
    return if (Constants.USER_LANGUAGE == Constants.ENGLISH_LANG){
        painterResource(id = R.drawable.digi_red)
    }else{
        painterResource(id = R.drawable.digi_red)
    }
}