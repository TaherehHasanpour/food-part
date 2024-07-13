package com.example.myapplication.network.Search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodItem(
    foodName: String,
    cookTime: Int?,
    foodImage: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.clickable {

        }
    ) {
        AsyncImage(
            model = foodImage,
            contentDescription = "$foodName's image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(150.dp)
                .height(136.5.dp)
                .aspectRatio(1.6f)
                .clip(shape = MaterialTheme.shapes.medium),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text(
                modifier = Modifier.basicMarquee(),
                text = foodName,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
            )
            Text(
                modifier = Modifier.basicMarquee(),
                text = cookTime.toString(),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
            )

        }
    }
}
