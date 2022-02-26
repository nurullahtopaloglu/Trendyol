package com.example.trendyol.network.util

import android.util.Log
import com.turkcell.android.network.base.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

inline fun <ResultType> requestNetwork(
    crossinline fetch: suspend () -> NetworkResult<ResultType>,
) = flow<NetworkResult<ResultType>> {

    emit(NetworkResult.Loading())

    try {
        val fetchData = fetch()
        emit(fetchData)
    } catch (throwable: Throwable) {
        Log.e("TRENDYOL", "Error: $throwable")
        emit(NetworkResult.Error(throwable.message!!, null))
    }
}