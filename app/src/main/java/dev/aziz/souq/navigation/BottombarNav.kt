package dev.aziz.souq.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.aziz.souq.R


@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        Triple(Screen.Profile, stringResource(R.string.profile), Icons.Default.Person),
        Triple(Screen.Home, stringResource(R.string.home), Icons.Default.Home),
        Triple(Screen.Category, stringResource(R.string.category), Icons.Default.Category),
        Triple(Screen.Cart, stringResource(R.string.cart), Icons.Default.ShoppingCart)
    )

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(horizontal = 18.dp, vertical = 15.dp)
            .wrapContentSize(),
        shape = RoundedCornerShape(50.dp),
        color = Color.White.copy(alpha = 0.92f),
        shadowElevation = 20.dp
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(0.dp, 5.dp, 0.dp, 10.dp)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { (screen, label, icon) ->
                NavigationBarItem(
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(icon, contentDescription = label) },
                    label = { Text(label) }
                )
            }
        }
    }
}