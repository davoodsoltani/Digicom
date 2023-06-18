package com.hads.digicom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hads.digicom.ui.screens.basket.BasketScreen
import com.hads.digicom.ui.screens.category.CategoryScreen
import com.hads.digicom.ui.screens.home.HomeScreen
import com.hads.digicom.ui.screens.home.WebPageScreen
import com.hads.digicom.ui.screens.profile.ProfileScreen
import com.hads.digicom.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }
        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }

        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(navController = navController, url = url)
            }
        }
    }
}