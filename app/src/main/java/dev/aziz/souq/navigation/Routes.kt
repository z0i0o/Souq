package dev.aziz.souq.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.aziz.souq.ui.cart.CartScreen
import dev.aziz.souq.ui.category.CategoryScreen
import dev.aziz.souq.ui.home.HomeScreen
import dev.aziz.souq.ui.profile.ProfileScreen

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object Home : Screen("home")
    object Category : Screen("category")
    object Cart : Screen("cart")
}

private fun getScreenIndex(route: String?): Int {
    return when (route) {
        Screen.Profile.route -> 0
        Screen.Home.route -> 1
        Screen.Category.route -> 2
        Screen.Cart.route -> 3
        else -> 0
    }
}

@Composable
fun SouqNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
        enterTransition = {
            val initialIndex = getScreenIndex(initialState.destination.route)
            val targetIndex = getScreenIndex(targetState.destination.route)

            val direction = if (targetIndex > initialIndex) {
                AnimatedContentTransitionScope.SlideDirection.Left
            } else {
                AnimatedContentTransitionScope.SlideDirection.Right
            }

            slideIntoContainer(
                towards = direction,
                animationSpec = tween(durationMillis = 450)
            )
        },

        exitTransition = {
            val initialIndex = getScreenIndex(initialState.destination.route)
            val targetIndex = getScreenIndex(targetState.destination.route)

            val direction = if (targetIndex > initialIndex) {
                AnimatedContentTransitionScope.SlideDirection.Left
            } else {
                AnimatedContentTransitionScope.SlideDirection.Right
            }

            slideOutOfContainer(
                towards = direction,
                animationSpec = tween(durationMillis = 450)
            )
        }
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Cart.route) { CartScreen() }
        composable(Screen.Category.route) { CategoryScreen() }
    }
}