package com.example.myapplication.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import ir.partsoftware.programmingquote.ui.theme.Shapes


 val darkColor = darkColors(
     primary= primary,
     secondary= secondary,
     background= background
     ,surface= surface,
    onBackground= onBackground
     ,onSurface= onSurface
 )


@Composable
fun ProgrammingfoodPartTheme(
    content: @Composable () -> Unit
) {
        MaterialTheme(
            colors = darkColor,
            typography = typography,
            shapes = Shapes,
            content = content
        )

}


