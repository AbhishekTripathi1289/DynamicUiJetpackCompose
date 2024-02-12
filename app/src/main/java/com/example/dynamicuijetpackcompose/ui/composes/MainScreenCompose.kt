package com.example.dynamicuijetpackcompose.ui.composes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.dynamicuijetpackcompose.R
import com.example.dynamicuijetpackcompose.ui.theme.DarkOrange
import com.example.starbuckapp.viewmodel.DynamicUiViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenCompose(viewModel: DynamicUiViewModel) {

    Scaffold(topBar = {
        TopAppBar(title = {
                Text(
                    text = stringResource(R.string.dynamic_ui),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = DarkOrange))
    }) { paddingvalues->

        LaunchedEffect(key1 = Unit){
            viewModel.fetchDynamicUiJson()
        }
        /*Handle all cases of api*/
        when(val result = viewModel._dynamicUiLiveData.value)
        {
            is DataState.Loading ->
            {
                LoadingCompose()
            }
            is DataState.Success ->
            {
                /*Load our UI*/
                RootCompose(modifier = Modifier.background(Color.White).padding(paddingvalues).fillMaxWidth().padding(16.dp), viewModel = viewModel, dynamicUiResponse = result.data)
            }
            is DataState.Error ->
            {
                /*Show message for Error Case*/
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = result.message.toString())
                }
            }
            is DataState.Empty ->
            {
                
            }
        }
    }
}