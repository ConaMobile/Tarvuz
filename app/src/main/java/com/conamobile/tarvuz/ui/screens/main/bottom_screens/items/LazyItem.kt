package com.conamobile.tarvuz.ui.screens.main.bottom_screens.items

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import com.conamobile.tarvuz.util.custom.CustomSpacer

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LazyItem(posts: Posts) {
    val painter = rememberImagePainter(
        data = posts.photo,
        builder = {
            placeholder(R.drawable.ic_watermelon_3)
            error(R.drawable.ic_watermelon_1)
            crossfade(500)
//            RoundedCornersTransformation(50f)
        }
    )
    val painterState = painter.state
    Card(modifier = Modifier
        .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp) {
        Column(modifier = Modifier
            .fillMaxSize()
            .clickable { }
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
            Image(painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp))
//            AnimatedVisibility(visible = painterState is ImagePainter.State.Loading) {
//                CircularProgressIndicator()
//            }
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

//@Preview(
//    name = "Night Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
//
//@Preview(
//    name = "Light Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//)
//
//@Composable
//private fun LazyItemPreview() {
//    LazyItem(posts = Posts(photo = "",
//        name = "Komputer Core i5",
//        price = "2.000.000 so'm",
//        time = "Kecha 20:00 da",
//        "Margilan",
//        condition = false))
//}