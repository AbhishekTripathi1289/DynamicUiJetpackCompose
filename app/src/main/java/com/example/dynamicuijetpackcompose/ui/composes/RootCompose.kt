package com.example.dynamicuijetpackcompose.ui.composes

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.dynamicuijetpackcompose.models.DynamicUiResponse
import com.example.dynamicuijetpackcompose.ui.theme.DarkOrange
import com.example.dynamicuijetpackcompose.utils.AppHelper
import com.example.dynamicuijetpackcompose.utils.DataValidation
import com.example.starbuckapp.utils.Constants
import com.example.starbuckapp.utils.Constants.ADDITIONAL_DATA
import com.example.starbuckapp.utils.Constants.API
import com.example.starbuckapp.viewmodel.DynamicUiViewModel
import org.json.JSONObject

@Composable
fun RootCompose(modifier: Modifier, viewModel: DynamicUiViewModel, dynamicUiResponse: DynamicUiResponse) {

    var isDataValid = remember {
        mutableStateOf(DataValidation.EMPTY)
    }

    /*Json Request*/
    var jsonObjectRequest = remember {
        JSONObject()
    }
    val activity = LocalContext.current as Activity

    LazyColumn(modifier = modifier)
    {
        itemsIndexed(dynamicUiResponse.form) {index, item ->
            when(item.type)
            {
                Constants.TEXT ->
                {
                    TextViewComposable(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp), item)
                }
                Constants.EDIT_TEXT ->
                {
                    EditTextComposable(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp), item, isDataValid){
                        jsonObjectRequest.put(item.label, it)
                    }
                }
                Constants.DROP_DOWN ->
                {
                    DropDownMenuCompose(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp), item, isDataValid){dropDownData, additionnalData ->
                        jsonObjectRequest.put(item.label, dropDownData)
                        jsonObjectRequest.put(ADDITIONAL_DATA, dropDownData)

                    }
                }
                Constants.IMAGE_VIEW ->
                {
                    ImageComposable(modifier = Modifier.fillMaxWidth(), item)
                }
                Constants.BUTTON ->
                {
                    ButtonWithCornerComposable(modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                        backGroundColor = DarkOrange, foregroundColor = Color.Black,
                        shape = CircleShape
                        ,fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        text = "Get Started",
                        formItem = item, textModiFier = Modifier.padding(vertical = 5.dp)
                    ){
                        isDataValid.value = DataValidation.CHECK
                        if(AppHelper.isInternetConnected(activity)  && item.action?.type.equals(API))
                        {
                           viewModel.submitRequest(item.action, jsonObjectRequest)
                        }
                        else
                        {
                            Toast.makeText(activity, "Please check your internet connection", Toast.LENGTH_LONG).show()

                        }
                    }
                }
            }
        }
    }

    /*Handle all Api cases*/
    when(val data = viewModel._getApiLiveData.value){
        DataState.Empty -> {
        }
        is DataState.Error -> {
            Toast.makeText(activity, "Api Fail with message ${data.message}", Toast.LENGTH_LONG).show()
        }
        DataState.Loading ->
        {
            LoadingCompose()
        }
        is DataState.Success -> {
            Toast.makeText(activity, "Api Success", Toast.LENGTH_LONG).show()
        }
    }
}