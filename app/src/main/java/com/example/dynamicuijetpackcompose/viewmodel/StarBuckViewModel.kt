package com.example.starbuckapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.dynamicuijetpackcompose.models.Action
import com.example.dynamicuijetpackcompose.models.DynamicUiResponse
import com.example.starbuckapp.repo.DynamicUiRepo
import com.example.starbuckapp.utils.Constants.GET
import com.example.starbuckapp.utils.Constants.POST
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DynamicUiViewModel @Inject constructor(private val starBucksRepo: DynamicUiRepo) : ViewModel() {
    private val dynamicUiLiveData: MutableState<DataState<DynamicUiResponse>> = mutableStateOf(DataState.Empty)

    val _dynamicUiLiveData
        get() = dynamicUiLiveData

    private val getApiLiveData: MutableState<DataState<JSONObject>> = mutableStateOf(DataState.Empty)

    /*Observe these state when required*/
    val _getApiLiveData
        get() = getApiLiveData

    private val postApiLiveData: MutableState<DataState<JSONObject>> = mutableStateOf(DataState.Empty)

    /*Observe these state when required*/
    val _postApiLiveData
        get() = postApiLiveData

    /*Fetch our initial data for Dynamic Ui*/
    fun fetchDynamicUiJson()
    {
        viewModelScope.launch {
            starBucksRepo.fetchDynamicUiJson().onEach {
                dynamicUiLiveData.value = it
            }.launchIn(viewModelScope)
        }
    }

    /*For both GET and POST*/
    fun submitRequest(action: Action, jsonObjectReqest: JSONObject)
    {
        viewModelScope.launch {
            when(action.method)
            {
                GET->{
                    starBucksRepo.getRequest(action.endpoint,  action.headers).onEach {
                        getApiLiveData.value = it
                    }.launchIn(viewModelScope)
                }
                POST->{
                    starBucksRepo.postRequest(action.endpoint,  action.headers, jsonObjectReqest).onEach {
                        postApiLiveData.value = it
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}