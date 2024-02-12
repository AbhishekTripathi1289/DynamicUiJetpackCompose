package com.example.starbuckapp.repo


import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.dynamicuijetpackcompose.api.DynamicUiApi
import com.example.dynamicuijetpackcompose.models.DynamicUiResponse
import com.example.dynamicuijetpackcompose.utils.AssetManager
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class DynamicUiRepo @Inject constructor(val assetManager: AssetManager, var dynamicUiApi: DynamicUiApi) {

    /*fetch data from Local json file*/
    suspend fun fetchDynamicUiJson() : Flow<DataState<DynamicUiResponse>> {

        return flow {
            try {
                emit(DataState.Loading)
                /*I add this delay so it take some time to show loader because data is coming from local json*/
                delay(2000)
                val response = assetManager.loadAssetFromFile()

                /*handle all cases success error exception*/
                if(response != null)
                {
                    emit(DataState.Success(response))
                }
                else{
                    emit(DataState.Error(message = "No Data Found"))
                }
            }
            catch (ioException: IOException) {

                ioException.printStackTrace()
                emit(DataState.Error(message = ioException.message))

            } catch (jsonParseException: JsonParseException) {

                jsonParseException.printStackTrace()
                emit(DataState.Error(message = jsonParseException.message))

            } catch (exception: Exception) {

                exception.printStackTrace()
                emit(DataState.Error(message = exception.message))
            }
        }
    }

    /*Method for get type API*/
    fun getRequest(endPoint: String, headers: Map<String, String>?): Flow<DataState<JSONObject>>{

        return flow {
            try {
                emit(DataState.Loading)
                var data: Response<JSONObject>? = null
                if(headers == null)
                {
                    data = dynamicUiApi.getDataWithOutHeader(endPoint).execute()

                }else
                {
                    data = dynamicUiApi.getDataWithHeader(endPoint, headers).execute()
                }

                /*handle all cases for api success error exception*/
                if(data.isSuccessful && data.body()!= null) {

                    data.body()?.let {
                        emit(DataState.Success(it))
                    }
                }
                else if(data.errorBody() != null)
                {
                    val errorObj = JSONObject(data.errorBody()!!.charStream().readText())
                    emit(DataState.Error(message = errorObj.getString("message")))
                }
                else
                {

                    emit(DataState.Error(message = "Something Went Wrong"))
                }
            } catch (exception: Exception)
            {
                exception.printStackTrace()
                emit(DataState.Error(message = exception.message))
            }
        }.flowOn(Dispatchers.IO)
    }

    /*Method for POSt type API*/
    fun postRequest(endPoint: String, headers: Map<String, String>, jsonObjectRequest: JSONObject): Flow<DataState<JSONObject>>{

        return flow {
            try {
                emit(DataState.Loading)
                var data: Response<JSONObject>? = null

                if(headers == null)
                {
                    data = dynamicUiApi.postDataWithoutHeader(endPoint , jsonObjectRequest).execute()

                }else
                {
                    data = dynamicUiApi.postDataWithHeader(endPoint, headers, jsonObjectRequest).execute()
                }
                /*handle all cases for api success error exception*/
                if(data.isSuccessful && data.body()!= null) {
                    data.body()?.let {
                        emit(DataState.Success(it))
                    }
                }
                else if(data.errorBody() != null)
                {
                    val errorObj = JSONObject(data.errorBody()!!.charStream().readText())
                    emit(DataState.Error(message = errorObj.getString("message")))
                }
                else
                {
                    emit(DataState.Error(message = "Something Went Wrong"))
                }
            } catch (exception: Exception)
            {
                emit(DataState.Error(message = exception.message))
            }
        }.flowOn(Dispatchers.IO)
    }
}