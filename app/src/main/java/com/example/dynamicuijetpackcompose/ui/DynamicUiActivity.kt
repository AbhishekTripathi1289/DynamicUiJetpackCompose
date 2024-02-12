package com.example.dynamicuijetpackcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dynamicuijetpackcompose.ui.composes.MainScreenCompose
import com.example.dynamicuijetpackcompose.ui.theme.DynamicUiJetpackComposeTheme
import com.example.starbuckapp.viewmodel.DynamicUiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DynamicUiActivity : ComponentActivity() {
    val viewModel: DynamicUiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicUiJetpackComposeTheme {
                /*Main Starting Compose*/
                MainScreenCompose(viewModel)
            }
        }
    }
}