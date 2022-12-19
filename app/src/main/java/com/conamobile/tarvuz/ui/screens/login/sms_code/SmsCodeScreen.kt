package com.conamobile.tarvuz.ui.screens.login.sms_code

import android.app.Activity
import android.os.CountDownTimer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.nav.Screen
import com.conamobile.tarvuz.ui.screens.login.mvvm.vm.LoginViewModel
import com.conamobile.tarvuz.ui.screens.login.phone_number.auth.ResultState
import com.conamobile.tarvuz.ui.theme.*
import com.conamobile.tarvuz.util.custom.CustomExitDialog
import com.conamobile.tarvuz.util.custom.CustomSpacer
import com.conamobile.tarvuz.util.custom.ParentView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun SmsCodeScreen(navController: NavController, phoneNumber: String, activity: Activity) {
    val code1 = remember { mutableStateOf(TextFieldValue()) }
    val code2 = remember { mutableStateOf(TextFieldValue()) }
    val code3 = remember { mutableStateOf(TextFieldValue()) }
    val code4 = remember { mutableStateOf(TextFieldValue()) }
    val code5 = remember { mutableStateOf(TextFieldValue()) }
    val code6 = remember { mutableStateOf(TextFieldValue()) }
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }
    val focusRequester5 = remember { FocusRequester() }
    val focusRequester6 = remember { FocusRequester() }
    val enable1 = remember { mutableStateOf(true) }
    val enable2 = remember { mutableStateOf(true) }
    val enable3 = remember { mutableStateOf(true) }
    val enable4 = remember { mutableStateOf(true) }
    val enable5 = remember { mutableStateOf(true) }
    val enable6 = remember { mutableStateOf(true) }
    val smsState = remember { mutableStateOf(ResourceState.DEFAULT) }
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    val viewModel = hiltViewModel<LoginViewModel>()
    val buttonEnable = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sendFailureMessage = remember { mutableStateOf("") }
    val showExitDialog = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        focusRequester1.requestFocus()
        keyboard?.show()
        startTimer(buttonEnable, viewModel.secondTimer)
    }

    ParentView(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp)) {
        Column(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .align(Alignment.Center)
            .fillMaxWidth()) {

            Text(text = stringResource(R.string.input_sms_code),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.CenterHorizontally))

            CustomSpacer(height = 10.dp)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                //1
                SmsCodeTypeView(stateSms = smsState,
                    code = code1,
                    focusRequester = focusRequester1,
                    action = {
                        focusRequester2.requestFocus()
                        checkSmsCode(code1 = code1,
                            code2 = code2,
                            code3 = code3,
                            code4 = code4,
                            code5 = code5,
                            code6 = code6,
                            focusManager = focusManager,
                            keyboard = keyboard,
                            viewModel = viewModel,
                            enable1,
                            enable2,
                            enable3,
                            enable4,
                            enable5,
                            enable6,
                            smsState,
                            scope,
                            navController)
                    },
                    enable = enable1.value)
                Spacer(modifier = Modifier.width(15.dp))
                //2
                SmsCodeTypeView(stateSms = smsState,
                    code = code2,
                    focusRequester = focusRequester2,
                    action = {
                        focusRequester3.requestFocus()
                        checkSmsCode(code1 = code1,
                            code2 = code2,
                            code3 = code3,
                            code4 = code4,
                            code5 = code5,
                            code6 = code6,
                            focusManager = focusManager,
                            keyboard = keyboard,
                            viewModel = viewModel,
                            enable1,
                            enable2,
                            enable3,
                            enable4,
                            enable5,
                            enable6,
                            smsState,
                            scope,
                            navController)
                    },
                    enable = enable2.value)
                Spacer(modifier = Modifier.width(15.dp))
                //3
                SmsCodeTypeView(stateSms = smsState,
                    code = code3,
                    focusRequester = focusRequester3,
                    action = {
                        focusRequester4.requestFocus()
                        checkSmsCode(code1 = code1,
                            code2 = code2,
                            code3 = code3,
                            code4 = code4,
                            code5 = code5,
                            code6 = code6,
                            focusManager = focusManager,
                            keyboard = keyboard,
                            viewModel = viewModel,
                            enable1,
                            enable2,
                            enable3,
                            enable4,
                            enable5,
                            enable6,
                            smsState,
                            scope,
                            navController)
                    },
                    enable = enable3.value)
                Spacer(modifier = Modifier.width(15.dp))
                //4
                SmsCodeTypeView(stateSms = smsState,
                    code = code4,
                    focusRequester = focusRequester4,
                    action = {
                        focusRequester5.requestFocus()
                        checkSmsCode(code1 = code1,
                            code2 = code2,
                            code3 = code3,
                            code4 = code4,
                            code5 = code5,
                            code6 = code6,
                            focusManager = focusManager,
                            keyboard = keyboard,
                            viewModel = viewModel,
                            enable1,
                            enable2,
                            enable3,
                            enable4,
                            enable5,
                            enable6,
                            smsState,
                            scope,
                            navController)
                    },
                    enable = enable4.value)
                Spacer(modifier = Modifier.width(15.dp))
                //5
                SmsCodeTypeView(stateSms = smsState,
                    code = code5,
                    focusRequester = focusRequester5,
                    action = {
                        focusRequester6.requestFocus()
                        checkSmsCode(code1 = code1,
                            code2 = code2,
                            code3 = code3,
                            code4 = code4,
                            code5 = code5,
                            code6 = code6,
                            focusManager = focusManager,
                            keyboard = keyboard,
                            viewModel = viewModel,
                            enable1,
                            enable2,
                            enable3,
                            enable4,
                            enable5,
                            enable6,
                            smsState,
                            scope,
                            navController)
                    },
                    enable = enable5.value)
                Spacer(modifier = Modifier.width(15.dp))
                //6
                SmsCodeTypeView(stateSms = smsState,
                    code = code6,
                    focusRequester = focusRequester6,
                    action = {
                        checkSmsCode(code1 = code1,
                            code2 = code2,
                            code3 = code3,
                            code4 = code4,
                            code5 = code5,
                            code6 = code6,
                            focusManager = focusManager,
                            keyboard = keyboard,
                            viewModel = viewModel,
                            enable1,
                            enable2,
                            enable3,
                            enable4,
                            enable5,
                            enable6,
                            smsState,
                            scope,
                            navController)
                    },
                    enable = enable6.value)
            }

            CustomSpacer(height = 10.dp)

            AnimatedVisibility(visible = smsState.value == ResourceState.LOADING || smsState.value == ResourceState.ERROR,
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = when (smsState.value) {
                    ResourceState.LOADING -> "Tekshirilyabdi..."
                    ResourceState.ERROR -> "Kod xato, tekshirib qaytadan tering"
                    else -> ""
                }, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            CustomSpacer(height = 10.dp)

            AnimatedVisibility(visible = smsState.value == ResourceState.ERROR) {
                Text(text = "Barchasini tozalash", modifier = Modifier.clickable {
                    code1.value = TextFieldValue()
                    code2.value = TextFieldValue()
                    code3.value = TextFieldValue()
                    code4.value = TextFieldValue()
                    code5.value = TextFieldValue()
                    code6.value = TextFieldValue()
                    focusRequester1.requestFocus()
                    keyboard?.show()
                })
            }
        }

        val animatedButtonColor =
            animateColorAsState(targetValue = if (buttonEnable.value) MaterialTheme.colors.primary else Color.LightGray,
                animationSpec = tween(200, 0, LinearEasing))

        Button(onClick = {
            //sms resend
            scope.launch(Dispatchers.Main) {
                viewModel.signInWithPhone(viewModel.phoneNumber.value, activity).collect {
                    when (it) {
                        is ResultState.Success -> startTimer(buttonEnable, viewModel.secondTimer)
                        is ResultState.Failure -> {
                            sendFailureMessage.value = it.msg.toString()
                            showExitDialog.value = true
                        }
                        ResultState.Loading -> {}
                    }
                }
            }
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
            enabled = buttonEnable.value) {
            Text(text = "Smsni qayta jo'natish ${viewModel.secondTimer.value}")
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

fun startTimer(buttonEnable: MutableState<Boolean>, secondTimer: MutableState<String>) {
    buttonEnable.value = false
    val cTimer = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            secondTimer.value = "(" + (millisUntilFinished / 1000).toString() + ")"
        }

        override fun onFinish() {
            secondTimer.value = ""
            buttonEnable.value = true
        }
    }
    cTimer.start()
}

fun enableAllTextField(
    enable1: MutableState<Boolean>,
    enable2: MutableState<Boolean>,
    enable3: MutableState<Boolean>,
    enable4: MutableState<Boolean>,
    enable5: MutableState<Boolean>,
    enable6: MutableState<Boolean>,
) {
    enable1.value = true
    enable2.value = true
    enable3.value = true
    enable4.value = true
    enable5.value = true
    enable6.value = true
}

fun disableAllTextField(
    enable1: MutableState<Boolean>,
    enable2: MutableState<Boolean>,
    enable3: MutableState<Boolean>,
    enable4: MutableState<Boolean>,
    enable5: MutableState<Boolean>,
    enable6: MutableState<Boolean>,
) {
    enable1.value = false
    enable2.value = false
    enable3.value = false
    enable4.value = false
    enable5.value = false
    enable6.value = false
}

@ExperimentalComposeUiApi
fun checkSmsCode(
    code1: MutableState<TextFieldValue>,
    code2: MutableState<TextFieldValue>,
    code3: MutableState<TextFieldValue>,
    code4: MutableState<TextFieldValue>,
    code5: MutableState<TextFieldValue>,
    code6: MutableState<TextFieldValue>,
    focusManager: FocusManager,
    keyboard: SoftwareKeyboardController?,
    viewModel: LoginViewModel,
    enable1: MutableState<Boolean>,
    enable2: MutableState<Boolean>,
    enable3: MutableState<Boolean>,
    enable4: MutableState<Boolean>,
    enable5: MutableState<Boolean>,
    enable6: MutableState<Boolean>,
    smsState: MutableState<ResourceState>,
    scope: CoroutineScope,
    navController: NavController,
) {
    if (allCodeIsNotEmpty(code1, code2, code3, code4, code5, code6)) {
        scope.launch(Dispatchers.Main) {
            viewModel.checkSmsCode(code1.value.text + code2.value.text + code3.value.text + code4.value.text + code5.value.text + code6.value.text)
                .collect {
                    when (it) {
                        is ResultState.Loading -> {
                            disableAllTextField(enable1,
                                enable2,
                                enable3,
                                enable4,
                                enable5,
                                enable6)
                            smsState.value = ResourceState.LOADING
                        }
                        is ResultState.Success -> {
                            disableAllTextField(enable1,
                                enable2,
                                enable3,
                                enable4,
                                enable5,
                                enable6)
                            smsState.value = ResourceState.SUCCESS
                            if (!viewModel.isFirstNavigation.value) {
                                viewModel.isFirstNavigation.value = true
                                navController.navigate(Screen.NameScreen.route)
                            }
                        }
                        is ResultState.Failure -> {
                            focusManager.clearFocus()
                            smsState.value = ResourceState.ERROR
                            enableAllTextField(enable1, enable2, enable3, enable4, enable5, enable6)
                        }
                    }
                }
        }
    } else {
        smsState.value = ResourceState.DEFAULT
    }
}

fun allCodeIsNotEmpty(
    code1: MutableState<TextFieldValue>,
    code2: MutableState<TextFieldValue>,
    code3: MutableState<TextFieldValue>,
    code4: MutableState<TextFieldValue>,
    code5: MutableState<TextFieldValue>,
    code6: MutableState<TextFieldValue>,
): Boolean {
    return code1.value.text.isNotEmpty() && code2.value.text.isNotEmpty() && code3.value.text.isNotEmpty() && code4.value.text.isNotEmpty() && code5.value.text.isNotEmpty() && code6.value.text.isNotEmpty()
}

@Composable
fun RowScope.SmsCodeTypeView(
    stateSms: MutableState<ResourceState>,
    code: MutableState<TextFieldValue>,
    focusRequester: FocusRequester,
    action: () -> Unit,
    enable: Boolean,
) {
    val animateColor = animateColorAsState(targetValue = when (stateSms.value) {
        ResourceState.DEFAULT -> DefaultYellow50
        ResourceState.LOADING -> DefaultYellow80
        ResourceState.SUCCESS -> DefaultGreen
        ResourceState.ERROR -> DefaultRed
    }, animationSpec = tween(200, 0, LinearEasing))

    Box(modifier = Modifier
        .border(border = BorderStroke(5.dp, animateColor.value), shape = RoundedCornerShape(10.dp))
        .height(65.dp)
        .fillMaxWidth()
        .weight(1F),
        contentAlignment = Alignment.Center) {

        BasicTextField(value = code.value,
            onValueChange = {
                if (it.text.length < 2) {
                    code.value =
                        TextFieldValue(text = it.text, selection = TextRange(it.text.length))
                }
                if (it.text.length == 1) {
                    action()
                }
            },
            enabled = enable,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp,
                textAlign = TextAlign.Center,
                color = if (isSystemInDarkTheme()) DefaultWhite else DefaultBlack),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .focusRequester(focusRequester))

    }
}


enum class ResourceState {
    DEFAULT, LOADING, SUCCESS, ERROR
}