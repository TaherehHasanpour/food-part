package com.example.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R


internal val typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileextrabold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileextrabold)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileextrabold)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileregular)),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileregular)),
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileregular)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileregular)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekanmobileregular)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),

)