package com.example.dynamicuijetpackcompose.ui.composes

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.dynamicuijetpackcompose.models.FormItem

@Composable
fun ButtonWithCornerComposable(modifier: Modifier, backGroundColor: Color,
                               foregroundColor: Color,
                               text: String,
                               fontSize: TextUnit,
                               fontWeight: FontWeight,
                               shape: Shape,
                               textModiFier: Modifier = Modifier,
                               formItem: FormItem,
                               callback: ()-> Unit) {


    Button(onClick = { callback.invoke() }, modifier = modifier, colors = ButtonDefaults.buttonColors(containerColor = backGroundColor,
        contentColor = foregroundColor), shape = shape ) {
        Text(text = formItem.label, fontSize = fontSize, fontWeight = fontWeight, modifier = textModiFier)
    }
}
