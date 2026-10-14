package dev.aziz.souq.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.aziz.souq.R
import dev.aziz.souq.ui.theme.BackgroundLight
import dev.aziz.souq.ui.theme.BlueDark
import dev.aziz.souq.ui.theme.BlueMain
import dev.aziz.souq.utils.validateEmail
import dev.aziz.souq.utils.validatePassword

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {


        Text(
            text = stringResource(R.string.login),
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = BlueDark
        )

        Spacer(modifier = Modifier.height(32.dp))


        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextButton(
            onClick = { navController.navigate("forget") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = stringResource(R.string.forget_password),
                color = Color.DarkGray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                val emailError = validateEmail(email)
                val passwordError = validatePassword(password)
                if (emailError != null) {
                    Toast.makeText(context, emailError, Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (passwordError != null) {
                    Toast.makeText(context, passwordError, Toast.LENGTH_SHORT).show()
                    return@Button
                }
                Toast.makeText(
                    context,
                    R.string.logged_in_successfully,
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate("scaffold")

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BlueMain)
        ) {
            Text(
                text = stringResource(R.string.login),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        TextButton(onClick = { navController.navigate("register") }) {
            Text(
                text = stringResource(R.string.create_new_account),
                color = BlueMain,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }


        TextButton(onClick = { navController.navigate(route = "scaffold") }) {
            Text(
                text = "skip",
                color = BlueMain,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}



