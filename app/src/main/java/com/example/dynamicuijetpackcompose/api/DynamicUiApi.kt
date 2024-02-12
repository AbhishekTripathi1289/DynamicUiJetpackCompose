package com.example.dynamicuijetpackcompose.api

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

/*Retrofit Interface*/
interface DynamicUiApi
{
    @GET
    fun getDataWithOutHeader(@Url url: String): Call<JSONObject>

    @GET
    fun getDataWithHeader(@Url url: String, @HeaderMap headers: Map<String, String>?): Call<JSONObject>

    @POST
    fun postDataWithoutHeader(@Url url: String, @Body requestBody: Any): Call<JSONObject>

    @POST
    fun postDataWithHeader(@Url url: String, @HeaderMap headers: Map<String, String>?, @Body requestBody: Any): Call<JSONObject>
}