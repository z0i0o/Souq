package dev.aziz.souq.navigation

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
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Category : Screen("category")
    object Cart : Screen("cart")
}

@Composable
fun SouqNavHost(navController: NavHostController , modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Home.route , modifier = modifier) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Cart.route) { CartScreen() }
        composable(Screen.Category.route) { CategoryScreen() }
    }
}
