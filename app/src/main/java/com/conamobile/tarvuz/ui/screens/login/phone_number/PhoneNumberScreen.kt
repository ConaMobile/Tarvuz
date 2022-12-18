package com.conamobile.tarvuz.ui.screens.login.phone_number

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.nav.Screen
import com.conamobile.tarvuz.ui.screens.login.di.vm.LoginViewModel
import com.conamobile.tarvuz.ui.screens.login.phone_number.auth.ResultState
import com.conamobile.tarvuz.ui.theme.DefaultDark
import com.conamobile.tarvuz.ui.theme.DefaultYellow50
import com.conamobile.tarvuz.util.MaskVisualTransformation
import com.conamobile.tarvuz.util.MySharedPreferences
import com.conamobile.tarvuz.util.custom.CustomExitDialog
import com.conamobile.tarvuz.util.custom.CustomSpacer
import com.conamobile.tarvuz.util.custom.LoginText
import com.conamobile.tarvuz.util.custom.ParentView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun PhoneNumberScreen(navController: NavController, activity: Activity) {
    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    val viewModel = hiltViewModel<LoginViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val sendFailureMessage = remember { mutableStateOf("") }
    val showExitDialog = remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        if (showKeyboard.value) {
            focusRequester.requestFocus()
            keyboard?.show()
        }
    }

    ParentView(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp)
        .pointerInput(Unit) {
            detectTapGestures(onPress = {
                keyboard?.hide()
            })
        }) {
        Column(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .align(Alignment.CenterStart)) {

            LoginText(text = stringResource(R.string.input_phone_number))

            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                backgroundColor = DefaultYellow50,
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp)) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)) {
                    Image(painter = painterResource(id = R.drawable.flag_uzbekistan),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "+998 | ",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold,
                        color = DefaultDark)

                    BasicTextField(value = TextFieldValue(viewModel.phoneNumber.value,
                        selection = TextRange(viewModel.phoneNumber.value.length)),
                        onValueChange = { value ->
                            if (value.text.length <= 9) {
                                viewModel.phoneNumber.value = value.text.filter { it.isDigit() }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        textStyle = TextStyle(fontWeight = FontWeight.Bold),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (viewModel.phoneNumber.value.isEmpty()) {
                                Text(text = "(90) 123-45-67", color = DefaultDark)
                            }
                            innerTextField()
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number),
                        visualTransformation = MaskVisualTransformation("(##) ###-##-##"),
                        keyboardActions = KeyboardActions(onDone = { keyboard?.hide() }))
                }
            }
        }

        CustomSpacer(height = 20.dp)

        val buttonEnable = viewModel.phoneNumber.value.length == 9
        val animatedButtonColor =
            animateColorAsState(targetValue = if (viewModel.phoneNumber.value.length == 9) MaterialTheme.colors.primary else Color.LightGray,
                animationSpec = tween(200, 0, LinearEasing))

        Button(onClick = {
            sendSmsFirebase(coroutineScope,
                viewModel,
                viewModel.phoneNumber.value,
                activity,
                keyboard,
                navController,
                showExitDialog,
                sendFailureMessage)
        },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = animatedButtonColor.value,
                disabledBackgroundColor = animatedButtonColor.value,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = buttonEnable) {
            Text(text = stringResource(R.string.next))
        }

        AnimatedVisibility(visible = showExitDialog.value) {
            keyboard?.hide()
            CustomExitDialog(topText = "Serverda xatolik",
                bottomText = "Biz ushbu xatolikni oldik:\n${sendFailureMessage.value}\nIltmos keyinroq urinib ko'ring!") {
                activity.finish()
            }
        }
    }
}

@ExperimentalComposeUiApi
fun sendSmsFirebase(
    scope: CoroutineScope,
    viewModel: LoginViewModel,
    phoneNumber: String,
    activity: Activity,
    keyboard: SoftwareKeyboardController?,
    navController: NavController,
    showExitDialog: MutableState<Boolean>,
    sendFailureMessage: MutableState<String>,
) {
    scope.launch(Dispatchers.Main) {
        viewModel.signInWithPhone(phoneNumber, activity).collect {
            when (it) {
                is ResultState.Success -> {
                    MySharedPreferences(activity).isUserPhone(viewModel.phoneNumber.value)
                    keyboard?.hide()
                    navController.navigate("${Screen.SmsCodeScreen.route}/${viewModel.phoneNumber.value}")
                }
                is ResultState.Failure -> {
                    showExitDialog.value = true
                    sendFailureMessage.value = it.msg.toString()
                }
                is ResultState.Loading -> {}
            }
        }
    }
}