package dev.aziz.souq.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.aziz.souq.R
import dev.aziz.souq.ui.theme.BackgroundLight
import dev.aziz.souq.ui.theme.TextPrimary


@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier,
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigation() },
        containerColor = BackgroundLight
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),

            contentPadding = PaddingValues(bottom = 120.dp)
        ) {
            items(50) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

// topbar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 28.sp,
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_menu_24),
                    contentDescription = "Menu",
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundLight),
    )
}


@Composable
fun BottomNavigation() {
    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(horizontal = 50.dp, vertical = 16.dp)
            .wrapContentSize(),
        shape = RoundedCornerShape(50.dp),
        color = Color.White.copy(alpha = 0.92f),
        shadowElevation = 12.dp
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(0.dp, 5.dp, 0.dp, 5.dp)
        ) {
            NavigationBarItem(
                icon = { Icon(painterResource(R.drawable.category), "Category") },
                label = { Text(stringResource(R.string.category)) },
                selected = false,
                onClick = { }
            )
            NavigationBarItem(
                icon = { Icon(painterResource(R.drawable.home), "Home") },
                label = { Text(stringResource(R.string.home)) },
                selected = true,
                onClick = { }
            )
            NavigationBarItem(
                icon = { Icon(painterResource(R.drawable.profile), "Profile") },
                label = { Text(stringResource(R.string.profile)) },
                selected = false,
                onClick = { }
            )
        }
    }
}