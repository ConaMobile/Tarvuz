package com.conamobile.tarvuz.ui.screens.login.permission

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conamobile.tarvuz.MainActivity
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.theme.DefaultGreen
import com.conamobile.tarvuz.ui.theme.DefaultPink
import com.conamobile.tarvuz.ui.theme.DefaultViolet
import com.conamobile.tarvuz.util.MySharedPreferences
import com.conamobile.tarvuz.util.custom.CustomSpacer
import com.conamobile.tarvuz.util.custom.ParentView
import com.google.accompanist.permissions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@Composable
fun PermissionScreen() {
    val locationPermissionState =
        rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val locationAllowed = remember { mutableStateOf(false) }
    val cameraAllowed = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    BackHandler(true) {}
    ParentView(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        Text(text = "Deyarli Tugadi!", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {

            PermissionCard("Joylashuvga ruxsat bering *",
                locationPermissionState,
                R.drawable.ic_location,
                click = {
                    locationPermissionState.launchPermissionRequest()
                },
                locationAllowed)

            PermissionCard("Kameraga ruxsat bering",
                cameraPermissionState,
                R.drawable.ic_camera,
                click = {
                    cameraPermissionState.launchPermissionRequest()
                },
                cameraAllowed)

            CustomSpacer(height = 20.dp)

            if (locationPermissionState.status.shouldShowRationale) {
                locationAllowed.value = true
            } else if (cameraPermissionState.status.shouldShowRationale) {
                cameraAllowed.value = true
            } else if (!cameraPermissionState.status.isGranted && cameraAllowed.value || !locationPermissionState.status.isGranted && locationAllowed.value) {
                Text(text = "Sozlamalarni ochish", modifier = Modifier.clickable {
                    Toast.makeText(context,
                        "Ruxsatlarnomalar bo'limidan kerakli ruxsatni bering!",
                        Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)))
                })
            }

        }

        val animatedButtonColor =
            animateColorAsState(targetValue = if (locationPermissionState.status.isGranted) MaterialTheme.colors.primary else Color.LightGray,
                animationSpec = tween(200, 0, LinearEasing))

        Button(onClick = {
            MySharedPreferences(context).isFirstLogin(false)
            coroutineScope.launch(Dispatchers.IO) {
                delay(500)
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                context.startActivity(intent)
                Runtime.getRuntime().exit(0)
            }
        },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = animatedButtonColor.value,
                disabledBackgroundColor = animatedButtonColor.value,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = locationPermissionState.status.isGranted) {
            Text(text = "Yakunlash")
        }

    }
}

@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@Composable
fun PermissionCard(
    acceptText: String,
    permissionState: PermissionState,
    iconDrawable: Int,
    click: () -> Unit,
    allowPermission: MutableState<Boolean>,
) {
    val allColor =
        if (!permissionState.status.isGranted && allowPermission.value) DefaultPink else if (permissionState.status.isGranted) DefaultGreen else DefaultViolet
    CustomSpacer(height = 20.dp)
    Text(text = acceptText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    CustomSpacer(height = 5.dp)

    Row(verticalAlignment = Alignment.CenterVertically) {

        Card(modifier = Modifier.height(50.dp),
            border = BorderStroke(5.dp, allColor),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                click()
            }) {

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)) {
                Icon(painter = painterResource(id = iconDrawable),
                    contentDescription = null,
                    tint = allColor)
                CustomSpacer(width = 8.dp)
                Spacer(modifier = Modifier
                    .width(3.dp)
                    .fillMaxHeight()
                    .background(allColor))
                CustomSpacer(width = 8.dp)
                Text(text = if (!permissionState.status.isGranted) "Ruxsat Berish" else "Ruxsat Berildi",
                    fontWeight = FontWeight.Bold,
                    color = allColor)
            }

        }
        CustomSpacer(width = 10.dp)
        AnimatedVisibility(visible = permissionState.status.isGranted) {
            Icon(painter = painterResource(id = R.drawable.ic_success),
                contentDescription = null,
                tint = allColor)
        }
    }

}
