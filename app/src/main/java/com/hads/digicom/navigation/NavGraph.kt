package com.hads.digicom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hads.digicom.ui.screens.basket.BasketScreen
import com.hads.digicom.ui.screens.category.CategoryScreen
import com.hads.digicom.ui.screens.checkout.CheckoutScreen
import com.hads.digicom.ui.screens.checkout.ConfirmPurchaseScreen
import com.hads.digicom.ui.screens.home.HomeScreen
import com.hads.digicom.ui.screens.home.WebPageScreen
import com.hads.digicom.ui.screens.product_detail.NewCommentScreen
import com.hads.digicom.ui.screens.product_detail.ProductDescriptionScreen
import com.hads.digicom.ui.screens.product_detail.ProductDetailScreen
import com.hads.digicom.ui.screens.product_detail.ProductTechnicalFeaturesScreen
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
        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }

        composable(route = Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("orderId")?.let { orderId ->
                it.arguments!!.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }


        }




        composable(route = Screen.NewComment.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("productName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("productName")?.let { productName ->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl ->
                        NewCommentScreen(
                            navController = navController,
                            productId = productId,
                            productName = productName,
                            imageUrl = imageUrl
                        )
                    }
                }
            }


        }


        composable(route = Screen.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                ProductDetailScreen(
                    navController = navController,
                    productId = productId
                )
            }

        }

        composable(route = Screen.ProductDescription.route + "/{description}",
            arguments = listOf(
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("description")?.let { description ->
                ProductDescriptionScreen(
                    navController = navController,
                    description = description
                )
            }

        }

        composable(route = Screen.ProductTechnicalFeatures.route + "/{jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductTechnicalFeaturesScreen(
                    navController = navController,
                    jsonString = jsonString
                )
            }

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