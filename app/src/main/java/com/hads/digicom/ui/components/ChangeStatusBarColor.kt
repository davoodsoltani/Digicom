package com.hads.digicom.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hads.digicom.navigation.Screen
import com.hads.digicom.ui.theme.Purple200

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