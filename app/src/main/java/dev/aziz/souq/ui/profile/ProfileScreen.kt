package dev.aziz.souq.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.aziz.souq.R
import dev.aziz.souq.data.model.Profile
import dev.aziz.souq.ui.theme.BackgroundLight
import dev.aziz.souq.ui.theme.BlueDark
import dev.aziz.souq.ui.theme.BlueLight
import dev.aziz.souq.ui.theme.BlueMain
import dev.aziz.souq.ui.theme.GrayCard
import dev.aziz.souq.ui.theme.LogoutBackground
import dev.aziz.souq.ui.theme.LogoutIconBackground
import dev.aziz.souq.ui.theme.LogoutTextAndIcon

//Fake data
val fakeProfile = Profile(
    id = 1,
    name = "عبدالعزيز طه",
    email = "abdelaziztaha99@gmail.com",
    phone = "01000000000",
    address = "Cairo, Egypt",
    image = "https://picsum.photos/300/300",
    points = 245,
    activeOrders = 12

)

@Composable
fun ProfileScreen(profile: Profile = fakeProfile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(BackgroundLight)
            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        //first part
        ImgProfile(profile = profile, onEditClick = {})
        Spacer(Modifier.heightIn(20.dp))

        //secend part
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatCard(
                modifier = Modifier.weight(1f),
                value = profile.points.toString(),
                label = stringResource(R.string.profile_reward_points),
                background = BlueLight,
                valueColor = BlueDark,
                labelColor = BlueMain
            )
            StatCard(
                modifier = Modifier.weight(1f),
                value = profile.activeOrders.toString(),
                label = stringResource(R.string.profile_active_orders),
                background = GrayCard,
                valueColor = BlueMain,
                labelColor = Color.DarkGray
            )
        }
        Spacer(Modifier.height(20.dp))

        //third part
        ProfileOptionsList()


    }
}

//fisrt part
@Composable
private fun ImgProfile(profile: Profile, onEditClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(150.dp)) {

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(36.dp))
                    .background(BlueMain)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {//img coil if not show person icon
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
                        modifier = Modifier.size(80.dp)
                    )
                }
            }

            // Edit button
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable(onClick = onEditClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.profile_edit),
                    tint = BlueMain,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        //name and email
        Spacer(Modifier.height(15.dp))
        Text(profile.name, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(2.dp))
        Text(profile.email, fontSize = 15.sp, color = Color.DarkGray)
    }
}


//secend part
@Composable
private fun StatCard(
    modifier: Modifier,
    value: String,
    label: String,
    background: Color,
    valueColor: Color,
    labelColor: Color,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(background)
            .padding(horizontal = 24.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(value, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = valueColor)
        Spacer(Modifier.height(4.dp))
        Text(label, fontSize = 14.sp, color = labelColor)
    }
}

//third part

@Composable
fun ProfileOptionItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    iconBackgroundColor: Color = Color(0xFFF2F4F7),
    textColor: Color = Color.Black,
    iconTint: Color = Color.DarkGray,
    showArrow: Boolean = true
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // دائرية خلف الأيقونة
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(iconBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }

            if (showArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileOptionsList(
    onOrdersClick: () -> Unit = {},
    onWishlistClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8F8))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // my order
        ProfileOptionItem(
            title = stringResource(R.string.MyOrder),
            icon = Icons.Default.ShoppingBag,
            onClick = onOrdersClick
        )

// Wishlist
        ProfileOptionItem(
            title = stringResource(R.string.wishlist),
            icon = Icons.Default.FavoriteBorder,
            onClick = onWishlistClick
        )

// Setttings
        ProfileOptionItem(
            title = stringResource(R.string.settings),
            icon = Icons.Default.Settings,
            onClick = onSettingsClick
        )


        // logout
        ProfileOptionItem(
            title = stringResource(R.string.logout),
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            onClick = onLogoutClick,
            backgroundColor = LogoutBackground,
            iconBackgroundColor = LogoutIconBackground,
            textColor = LogoutTextAndIcon,
            iconTint = LogoutTextAndIcon,
            showArrow = false
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

