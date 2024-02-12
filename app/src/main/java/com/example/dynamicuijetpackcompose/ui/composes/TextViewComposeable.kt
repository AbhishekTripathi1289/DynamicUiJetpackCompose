package com.example.dynamicuijetpackcompose.ui.composes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicuijetpackcompose.models.FormItem
import com.example.dynamicuijetpackcompose.ui.theme.MyCustomFont


@Composable
fun TextViewComposable(modifier: Modifier = Modifier,formItem: FormItem) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = formItem.label,
            modifier = Modifier.padding(end = 16.dp).weight(0.3f),
            fontSize = 18.sp, color = Color.Black, fontFamily = MyCustomFont, fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(text = formItem.text,
            modifier = Modifier.weight(0.7f),
            fontSize = 18.sp, color = Color.DarkGray,fontFamily = MyCustomFont, fontWeight = FontWeight.Normal
        )
    }
}