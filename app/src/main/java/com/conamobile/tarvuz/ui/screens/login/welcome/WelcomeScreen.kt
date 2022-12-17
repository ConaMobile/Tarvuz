package com.conamobile.tarvuz.ui.screens.login.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.nav.Screen
import com.conamobile.tarvuz.util.custom.LoginDefaultButton
import com.conamobile.tarvuz.util.custom.ParentView

@Composable
fun WelcomeScreen(navController: NavController) {
    ParentView(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp)) {

        Column(Modifier
            .fillMaxWidth()
            .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = R.drawable.ic_tarvuz_txt),
                contentDescription = null,
                modifier = Modifier.height(80.dp),
                contentScale = ContentScale.FillWidth)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Tez va oson soting va sotib oling!",
                fontSize = 30.sp,
                textAlign = TextAlign.Center)

        }

        LoginDefaultButton(text = "Boshlash",
            click = { navController.navigate(Screen.PhoneNumberScreen.route) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 20.dp, end = 20.dp))
    }
}