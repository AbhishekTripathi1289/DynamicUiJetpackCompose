package com.example.dynamicuijetpackcompose.ui.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dynamicuijetpackcompose.models.FormItem
import com.example.dynamicuijetpackcompose.R

/*Image Composable*/
@Composable
fun ImageComposable(modifier: Modifier = Modifier,formItem: FormItem) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = formItem.imageUrl,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
            contentDescription = "Image",
            modifier = Modifier.fillMaxWidth().height(formItem.imageHeight.dp)
        )
    }

}