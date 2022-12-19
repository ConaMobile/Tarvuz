package com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import com.conamobile.tarvuz.util.custom.CustomSpacer

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LazyItem(posts: Posts) {
    val painter = rememberImagePainter(data = posts.photo, builder = {
//        placeholder(R.drawable.ic_watermelon_3)
        error(R.drawable.ic_watermelon_1)
        crossfade(500)
        RoundedCornersTransformation(50f)
    })
    val painterState = painter.state
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), elevation = 5.dp) {
        Box {
            Column(modifier = Modifier
                .fillMaxSize()
                .clickable { }
                .padding(bottom = 8.dp)) {
                Image(painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 160.dp, max = 250.dp),
                    contentScale = ContentScale.FillWidth)
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)) {
                    CustomSpacer(height = 10.dp)
                    Text(text = posts.name, maxLines = 2, fontSize = 20.sp)
                    CustomSpacer(height = 5.dp)
                    Card(backgroundColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                        shape = RoundedCornerShape(10.dp)) {
                        Text(text = if (posts.condition) "Yangi" else "F/b")
                    }
                    CustomSpacer(height = 5.dp)
                    Text(text = posts.price, fontSize = 25.sp)
                    CustomSpacer(height = 10.dp)
                    Text(text = posts.location, fontSize = 15.sp)
                    CustomSpacer(height = 3.dp)
                    Text(text = posts.time, fontSize = 15.sp)
                }
            }
        }
    }
}