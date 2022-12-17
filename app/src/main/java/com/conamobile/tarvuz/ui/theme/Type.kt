package com.conamobile.tarvuz.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.conamobile.tarvuz.R

val Typography = Typography(
    body1 = TextStyle(fontFamily = FontFamily(Font(R.font.default_light),
        Font(R.font.default_regular),
        Font(R.font.default_medium),
        Font(R.font.default_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp),

//    button = TextStyle(
//        fontFamily = FontFamily(
//            Font(R.font.default_bold)
//        ),
//        fontWeight = FontWeight.W500,
//        fontSize = 14.sp
//    ),
/* Other default text styles to override
caption = TextStyle(
fontFamily = FontFamily.Default,
fontWeight = FontWeight.Normal,
fontSize = 12.sp
)
*/
)