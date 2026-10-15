package dev.aziz.souq.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.aziz.souq.ui.login.ForgetPasswordScreen
import dev.aziz.souq.ui.login.LoginScreen
import dev.aziz.souq.ui.login.RegisterScreen
import dev.aziz.souq.ui.scaffold.Scaffold
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object Profile : Screen
    @Serializable data object Home : Screen
    @Serializable data object Category : Screen
    @Serializable data object Cart : Screen
    @Serializable data object Orders : Screen
    @Serializable data object Wishlist : Screen
    @Serializable data object Settings : Screen
    @Serializable data object Login : Screen
    @Serializable data object Register : Screen
    @Serializable data object Forget : Screen
    @Serializable data object Scaffold : Screen
}

private fun getAuthScreenIndex(destination: NavDestination?): Int {
    if (destination == null) return 0
    return when {
        destination.hasRoute<Screen.Login>() -> 0
        destination.hasRoute<Screen.Register>() -> 1
        destination.hasRoute<Screen.Forget>() -> -1
        destination.hasRoute<Screen.Scaffold>() -> 3
        else -> 0
    }
}

@Composable
fun SouqNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {



    NavHost(
        navController = navController,
        startDestination = Screen.Login,
        modifier = modifier,
        enterTransition = {
            val initialIndex = getAuthScreenIndex(initialState.destination)
            val targetIndex = getAuthScreenIndex(targetState.destination)

            val direction = if (targetIndex > initialIndex) {
                AnimatedContentTransitionScope.SlideDirection.Left
            } else {
                AnimatedContentTransitionScope.SlideDirection.Right
            }

            slideIntoContainer(
                towards = direction,
                animationSpec = tween(durationMillis = 800)
            )
        },
        exitTransition = {
            val initialIndex = getAuthScreenIndex(initialState.destination)
            val targetIndex = getAuthScreenIndex(targetState.destination)

            val direction = if (targetIndex > initialIndex) {
                AnimatedContentTransitionScope.SlideDirection.Left
            } else {
                AnimatedContentTransitionScope.SlideDirection.Right
            }

            slideOutOfContainer(
                towards = direction,
                animationSpec = tween(durationMillis = 800)
            )
        }
    ) {
        composable<Screen.Login> { LoginScreen(navController) }
        composable<Screen.Register> { RegisterScreen(navController) }
        composable<Screen.Forget> { ForgetPasswordScreen(navController) }
        composable<Screen.Scaffold> { Scaffold(rootNavController = navController) }
    }
}