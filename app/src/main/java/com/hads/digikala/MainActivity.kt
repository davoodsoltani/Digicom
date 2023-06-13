package com.hads.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hads.digikala.R
import com.hads.digikala.navigation.BottomNavigationBar
import com.hads.digikala.navigation.SetupNavGraph
import com.hads.digikala.ui.components.AppConfig
import com.hads.digikala.ui.components.ChangeStatusBarColor
import com.hads.digikala.ui.theme.CharGameTheme
import com.hads.digikala.utils.Constants
import com.hads.digikala.utils.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharGameTheme {
                navController = rememberNavController()
                ChangeStatusBarColor(navController = navController)
                val context = LocalContext.current

                AppConfig()

                LocaleUtils.setLocale(context, Constants.USER_LANGUAGE)
                var direction = if (Constants.USER_LANGUAGE == Constants.ENGLISH_LANG){
                    LayoutDirection.Ltr
                }else{
                    LayoutDirection.Rtl
                }

                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Scaffold(
                        topBar = {
//                            TopBarView()
                        },
                        bottomBar = {
                            BottomNavigationBar(navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                })
                        },
                        drawerContent = {
                            Text(text = "Drower")
                        }
                    ) {
                        SetupNavGraph(navController = navController)
                    }
                }
            }
        }
    }


}

private var counter = mutableStateOf(0)

@Composable
fun MyButton() {
    Button(onClick = {
        counter.value++
    }) {
        Text(
            text = "Counter",
            color = Color.Black
        )
    }
}

@Composable
fun MyText() {
    var count by remember { counter }
    if (count >= 10) {
        Text(
            text = count.toString(),
            color = Color.Red
        )
    } else {
        Text(text = count.toString())
    }
}

@Composable
fun TopBarView() {
    TopAppBar(
        elevation = 5.dp,
        title = {
            Text(text = "TopBar")
        },
        backgroundColor = Color.LightGray,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = "")
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "")
            }
        })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyItem(title: String, description: String) {
    var expendedState by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300
                )
            ),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        onClick = {expendedState = !expendedState},
    ) {
        Column() {
            Box(modifier = Modifier.background(Color.White)) {
                Image(
                    painter = painterResource(id = R.drawable.digi_logo),
                    contentDescription = "",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                )
            }
            Text(text = title)
            Text(text = description)
            
            if (expendedState){
                Text(text = "hcbakhcbaksjhdbckaj cashbcashbc ac asbckjsahbckajshbcaksjhbc asjhcb ajkchb akjsdhbc akjshbc akjshdbc akjshdbc kajhbc akjhdbc akjhdbc akjshcb aksjdhbc kajhdbck j")
            }
        }
    }


}
