package com.conamobile.tarvuz.ui.screens.login.name

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.nav.Screen
import com.conamobile.tarvuz.ui.screens.login.mvvm.vm.LoginViewModel
import com.conamobile.tarvuz.ui.theme.DefaultDark
import com.conamobile.tarvuz.ui.theme.DefaultYellow50
import com.conamobile.tarvuz.util.MySharedPreferences
import com.conamobile.tarvuz.util.custom.LoginText
import com.conamobile.tarvuz.util.custom.ParentView

@ExperimentalComposeUiApi
@Composable
fun NameScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    LaunchedEffect(true) {
        focusRequester.requestFocus()
        keyboard?.show()
    }
    navController.enableOnBackPressed(false)
    ParentView(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp)
        .pointerInput(Unit) {
            detectTapGestures(onPress = {
                keyboard?.hide()
            })
        }) {
        Column(modifier = Modifier
            .padding(horizontal = 20.dp)
            .align(Alignment.CenterStart)) {

            LoginText(text = stringResource(R.string.input_name))

            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                backgroundColor = DefaultYellow50,
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp)) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {

                    BasicTextField(value = TextFieldValue(viewModel.clientName.value,
                        TextRange(viewModel.clientName.value.length)),
                        onValueChange = { value ->
                            if (value.text.length <= 21) {
                                viewModel.clientName.value =
                                    value.text.filter { it.isLetter() || it.isWhitespace() }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .padding(horizontal = 20.dp),
                        textStyle = TextStyle(fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (viewModel.clientName.value.isEmpty()) {
                                Text(text = stringResource(R.string.input_name_example),
                                    color = DefaultDark)
                            }
                            innerTextField()
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text),
                        keyboardActions = KeyboardActions(onDone = {
                            if (viewModel.clientName.value.length > 2) nextScreen(context,
                                navController,
                                keyboard,
                                viewModel)
                        }))

                }

            }
        }

        val buttonEnable = viewModel.clientName.value.length > 2
        val animatedButtonColor =
            animateColorAsState(targetValue = if (viewModel.clientName.value.length > 2) MaterialTheme.colors.primary else Color.LightGray,
                animationSpec = tween(200, 0, LinearEasing))

        Button(onClick = {
            nextScreen(context, navController, keyboard, viewModel)
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

    }
}

@ExperimentalComposeUiApi
fun nextScreen(
    context: Context,
    navController: NavController,
    keyboard: SoftwareKeyboardController?,
    viewModel: LoginViewModel,
) {
    MySharedPreferences(context).isUserName(viewModel.clientName.value)
    keyboard?.hide()
    navController.navigate(Screen.PermissionScreen.route) {
        popUpTo(Screen.NameScreen.route) {
            inclusive = true
        }
    }
}