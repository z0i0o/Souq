package dev.aziz.souq.ui.drawer

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.aziz.souq.R
import dev.aziz.souq.data.model.Profile
import dev.aziz.souq.ui.profile.fakeProfile
import dev.aziz.souq.ui.theme.BlueMain
import kotlinx.coroutines.launch


@Composable
fun NavDrawer(
    profile: Profile = fakeProfile,
    onOrdersClick: () -> Unit = {},
    onWishlistClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    content: @Composable (openDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf("الرئيسية") }


    val blurRadius by animateDpAsState(
        targetValue = if (drawerState.isOpen) 16.dp else 0.dp,
        label = "DrawerBlurAnimation"
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            scrimColor = Color.Black.copy(alpha = 0.32f),
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = Color.White,
                    drawerShape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                    modifier = Modifier.width(300.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // profile img
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(36.dp))
                                .background(BlueMain)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(32.dp))
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            if (profile.image.isNotBlank()) {
                                AsyncImage(
                                    model = profile.image,
                                    contentDescription = stringResource(R.string.profile_image),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = profile.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = profile.address, color = Color.Gray, fontSize = 12.sp)

                        Spacer(modifier = Modifier.height(6.dp))
                        Surface(
                            color = Color(0xFFE8EAF6),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "${profile.points} نقطة ",
                                color = Color(0xFF3F51B5),
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF5F5F5))
                    Spacer(modifier = Modifier.height(16.dp))


                    val menuItems = listOf(
                        "الرئيسية" to Icons.Default.Home,
                        "التصنيفات" to Icons.Default.Menu,
                        "طلباتي" to Icons.Default.List,
                        "قائمة الأمنيات" to Icons.Default.FavoriteBorder,
                        "الإعدادات" to Icons.Default.Settings
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        menuItems.forEach { (title, icon) ->
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = title,
                                        fontWeight = if (selectedItem == title) FontWeight.Bold else FontWeight.Normal,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = androidx.compose.ui.text.style.TextAlign.End
                                    )
                                },
                                selected = selectedItem == title,
                                onClick = {
                                    selectedItem = title
                                    when (title) {
                                        "طلباتي" -> onOrdersClick()
                                        "قائمة الأمنيات" -> onWishlistClick()
                                        "الإعدادات" -> onSettingsClick()
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                icon = { Icon(icon, contentDescription = title) },
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = Color(0xFFF0F2FA),
                                    selectedIconColor = Color(0xFF3F51B5),
                                    selectedTextColor = Color(0xFF3F51B5),
                                    unselectedIconColor = Color.Gray,
                                    unselectedTextColor = Color.Gray
                                ),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                            )
                        }
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp), color = Color(0xFFF5F5F5))

                        //logout
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    "تسجيل الخروج",
                                    color = Color(0xFFC62828),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.End
                                )
                            },
                            selected = false,
                            onClick = {
                                onLogoutClick()
                                scope.launch { drawerState.close() }
                            },
                            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout", tint = Color(0xFFC62828)) },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "SOUQ DEV BY AZIZ", color = Color.LightGray, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = blurRadius)
            ) {
                content {
                    scope.launch { drawerState.open() }
                }
            }
        }
    }
}