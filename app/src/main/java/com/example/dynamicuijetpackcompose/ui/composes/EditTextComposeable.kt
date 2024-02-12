package com.example.dynamicuijetpackcompose.ui.composes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicuijetpackcompose.models.FormItem
import com.example.dynamicuijetpackcompose.models.Validation
import com.example.dynamicuijetpackcompose.ui.theme.MyCustomFont
import com.example.dynamicuijetpackcompose.utils.DataValidation
import com.example.dynamicuijetpackcompose.utils.isDataInValid


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextComposable(modifier: Modifier = Modifier,formItem: FormItem, isdataValid: MutableState<DataValidation>, callback: (String)->Unit) {

    var typedText  = remember {
        mutableStateOf("")
    }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = formItem.label,
            modifier = Modifier
                .padding(end = 5.dp)
                .weight(0.3f),
            fontSize = 18.sp, color = Color.Black, fontFamily = MyCustomFont, fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        OutlinedTextField(value = typedText.value, onValueChange = {
            typedText.value = it
            callback.invoke(typedText.value)
        }, label = {
            Text(text = formItem.label)
        }, placeholder = {
            Text(text = formItem.hint)
        }, modifier = Modifier
            .fillMaxWidth()
            .weight(0.7f),
            isError = (isdataValid.value == DataValidation.CHECK && typedText.value.isDataInValid(formItem.validation))
            )

    }
}

