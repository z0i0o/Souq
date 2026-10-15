package dev.aziz.souq.ui.scaffold

import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.aziz.souq.R
import dev.aziz.souq.navigation.BottomNavigation
import dev.aziz.souq.navigation.Screen
import dev.aziz.souq.ui.cart.CartScreen
import dev.aziz.souq.ui.category.CategoryScreen
import dev.aziz.souq.ui.drawer.NavDrawer
import dev.aziz.souq.ui.home.HomeScreen
import dev.aziz.souq.ui.profile.ProfileScreen
import dev.aziz.souq.ui.topbar.SouqTopAppBar

private const val BOTTOM_BAR_ANIMATION_DURATION = 400


private fun getBottomBarIndex(destination: NavDestination?): Int {
    if (destination == null) return 1
    return when {
        destination.hasRoute<Screen.Profile>() -> 0
        destination.hasRoute<Screen.Home>() -> 1
        destination.hasRoute<Screen.Category>() -> 2
        destination.hasRoute<Screen.Cart>() -> 3
        else -> 1
    }
}


private fun bottomBarSlideDirection(
    initial: NavDestination?,
    target: NavDestination?
): AnimatedContentTransitionScope.SlideDirection {
    return if (getBottomBarIndex(target) > getBottomBarIndex(initial)) {
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        AnimatedContentTransitionScope.SlideDirection.Right
    }
}

@Composable
fun Scaffold(rootNavController: NavController) {

    val bottomNavController = rememberNavController()
    val context = LocalContext.current

    NavDrawer(
        onOrdersClick = { bottomNavController.navigate(Screen.Orders) },
        onWishlistClick = { bottomNavController.navigate(Screen.Wishlist) },
        onSettingsClick = { bottomNavController.navigate(Screen.Settings) },
        onLogoutClick = {
            Toast.makeText(context, R.string.logoutsuccess, Toast.LENGTH_SHORT).show()
            rootNavController.navigate(Screen.Login) {
                popUpTo<Screen.Scaffold> { inclusive = true }
            }
        }
    ) { openDrawer ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { SouqTopAppBar(onMenuClick = openDrawer) },
            containerColor = Color.Transparent,
            bottomBar = { BottomNavigation(navController = bottomNavController) }
        ) { innerPadding ->

            NavHost(
                navController = bottomNavController,
                startDestination = Screen.Home,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding()),

                enterTransition = {
                    slideIntoContainer(
                        towards = bottomBarSlideDirection(
                            initialState.destination,
                            targetState.destination
                        ),
                        animationSpec = tween(durationMillis = BOTTOM_BAR_ANIMATION_DURATION)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = bottomBarSlideDirection(
                            initialState.destination,
                            targetState.destination
                        ),
                        animationSpec = tween(durationMillis = BOTTOM_BAR_ANIMATION_DURATION)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = bottomBarSlideDirection(
                            initialState.destination,
                            targetState.destination
                        ),
                        animationSpec = tween(durationMillis = BOTTOM_BAR_ANIMATION_DURATION)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = bottomBarSlideDirection(
                            initialState.destination,
                            targetState.destination
                        ),
                        animationSpec = tween(durationMillis = BOTTOM_BAR_ANIMATION_DURATION)
                    )
                }
            ) {

                composable<Screen.Profile> { ProfileScreen() }
                composable<Screen.Home> { HomeScreen() }
                composable<Screen.Category> { CategoryScreen() }
                composable<Screen.Cart> { CartScreen() }

                composable<Screen.Orders> { /* OrdersScreen() */ }
                composable<Screen.Wishlist> { /* WishlistScreen() */ }
                composable<Screen.Settings> { /* SettingsScreen() */ }
            }
        }
    }
}