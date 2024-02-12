package com.example.dynamicuijetpackcompose.utils

import android.content.Context
import com.example.dynamicuijetpackcompose.models.DynamicUiResponse
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AssetManager @Inject constructor(@ApplicationContext var context: Context)
{
    /*Fetch data from json and return*/
    fun loadAssetFromFile():DynamicUiResponse
    {
        var dynamicUiResponse: DynamicUiResponse
        val inputStream = context.assets.open("dynamic_ui.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        dynamicUiResponse = gson.fromJson(json, DynamicUiResponse::class.java)
       return dynamicUiResponse
    }
}