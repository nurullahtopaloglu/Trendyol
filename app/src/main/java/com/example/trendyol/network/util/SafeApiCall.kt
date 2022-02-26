package com.example.trendyol.network.util

import com.turkcell.android.network.base.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Legacy service calls checks status in ui layer
 * returns directly service response
 */
suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T> {
    try {
        val apiResponse = call.invoke()
        if (apiResponse.isSuccessful) {
            val response = apiResponse.body()
            if (response != null) {
                return NetworkResult.Success(response) // http ok, data ok
            } else {
                val message = "Response has no body"
                return NetworkResult.Error(message) // http ok, response body null
            }
        }
        return NetworkResult.Error(apiResponse.message())
    } catch (e: Exception) {
        return handleException(e)
    }
}

private fun <T : Any> handleException(exception: Exception): NetworkResult<T> {
    return when (exception) {
        is ConnectException -> {
            NetworkResult.Error(CONNECT_EXCEPTION)
        }
        is UnknownHostException -> {
            NetworkResult.Error(UNKNOWN_HOST_EXCEPTION)
        }
        is SocketTimeoutException -> {
            NetworkResult.Error(SOCKET_TIME_OUT_EXCEPTION)
        }
        is HttpException -> {
            NetworkResult.Error(UNKNOWN_NETWORK_EXCEPTION)
        }
        else -> {
            NetworkResult.Error(UNKNOWN_NETWORK_EXCEPTION)
        }
    }
}