package com.example.dynamicuijetpackcompose.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicuijetpackcompose.models.FormItem
import com.example.dynamicuijetpackcompose.utils.DataValidation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuCompose(modifier: Modifier = Modifier, formItem: FormItem,
                        isdataValid: MutableState<DataValidation>, callback: (String, String)->Unit) {
    val context = LocalContext.current
    var expanded  =  remember { mutableStateOf(false) }
    var selectedText =  remember { mutableStateOf(formItem.dropdown_content[0]) }
    var femaleData = remember {
        mutableStateOf(listOf("House Wife", "Job"))
    }
    var maleData = remember {
        mutableStateOf(listOf("Business Man ", "Private Job"))
    }
    var selected = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(bottom = 20.dp)) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)


        ) {
            ExposedDropdownMenuBox(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
                expanded = expanded.value,
                onExpandedChange = {
                    expanded.value = !expanded.value
                },
            ) {
                TextField(
                    value = selectedText.value,
                    onValueChange = {

                    },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                    modifier = Modifier
                        .menuAnchor()
                        .background(Color.White)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },modifier = Modifier.background(
                        Color.White)
                ) {
                    formItem.dropdown_content.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item, color = Color.Black) },
                            onClick = {
                                selectedText.value = item
                                expanded.value = false
                                callback.invoke(item, selected.value)
                            }, modifier = Modifier.background(Color.White)
                        )
                    }
                }
            }
        }
        if(isdataValid.value == DataValidation.CHECK && selectedText.value.equals("Select"))
        {
            Text(text = "This field is required", fontSize = 16.sp, color = Color.Red)
        }
        else if(!selectedText.value.equals("Select"))
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                var list = if(selectedText.value.equals("Male")) maleData else femaleData
                list.value.forEach{
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
                        Text(text = it, textAlign = TextAlign.Center, color = Color.Black)
                        RadioButton(selected = it == selected.value,
                            onClick = { selected.value = it
                                    callback.invoke(selectedText.value, selected.value)},
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Blue, unselectedColor = Color.Black))
                    }
                }
            }
        }
    }
}