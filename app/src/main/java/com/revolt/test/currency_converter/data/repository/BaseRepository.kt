package com.revolt.test.currency_converter.data.repository

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {
    suspend fun <T: Any> execute(call : suspend () -> Response<T>, errorMessage: String): T? {
        val result = apiResult(call, errorMessage)
        var data: T? = null

        when(result) {
            is ApiResult.Success ->
                data = result.data
            is ApiResult.Error ->
                Log.e("", "")
        }
        return data
    }

    private suspend fun <T: Any> apiResult(call: suspend ()-> Response<T>, errorMessage: String) : ApiResult<T>{
        val response = call.invoke()
        if(response.isSuccessful) return ApiResult.Success(response.body()!!)

        return ApiResult.Error(IOException("Error Occurred during Api result, Custom ERROR - $errorMessage"))
    }
}