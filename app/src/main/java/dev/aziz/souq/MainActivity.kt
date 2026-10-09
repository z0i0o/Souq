package dev.aziz.souq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import dev.aziz.souq.navigation.BottomNavigation
import dev.aziz.souq.navigation.SouqNavHost
import dev.aziz.souq.ui.drawer.NavDrawer
import dev.aziz.souq.ui.topbar.SouqTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            NavDrawer(
                onOrdersClick = { },
                onWishlistClick = { },
                onSettingsClick = { },
                onLogoutClick = { }
            ) { openDrawer ->

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { SouqTopAppBar(onMenuClick = openDrawer) },
                    containerColor = Color.Transparent,
                    bottomBar = { BottomNavigation(navController) }
                ) { innerPadding ->

                    SouqNavHost(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
                    )

                }
            }
        }
    }
}

