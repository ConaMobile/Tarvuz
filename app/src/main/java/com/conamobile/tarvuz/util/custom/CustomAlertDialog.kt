package com.conamobile.tarvuz.util.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.conamobile.tarvuz.R

@Composable
fun CustomAlertDialog(
    topText: String,
    bottomText: String,
    leftButtonClick: () -> Unit,
    rightButtonClick: () -> Unit,
) {

    Dialog(onDismissRequest = { leftButtonClick() },
        properties = DialogProperties(dismissOnBackPress = false,
            dismissOnClickOutside = false)) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), elevation = 8.dp) {
            Column(Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Red.copy(alpha = 0.8F)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_tarvuz_txt),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth)
                }

                Text(text = topText,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp)

                Text(text = bottomText,
                    modifier = Modifier.padding(8.dp))

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(onClick = { leftButtonClick() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)) {
                        Text(text = "Cancel")
                    }


                    Button(onClick = { leftButtonClick() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)) {
                        Text(text = "Exit")
                    }
                }


            }
        }
    }
}