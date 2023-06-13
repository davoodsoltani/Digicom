package com.hads.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hads.digikala.navigation.Screen
import com.hads.digikala.ui.theme.Purple200

@Composable
fun ChangeStatusBarColor(
    navController: NavHostController
){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()

    when(navBackStackEntry?.destination?.route){
        Screen.Splash.route ->{
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Purple200
                )
            }
        }else ->{
        systemUiController.setStatusBarColor(
            color = Color.White
        )
        }
    }
}