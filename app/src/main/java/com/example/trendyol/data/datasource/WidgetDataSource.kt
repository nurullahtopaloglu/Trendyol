package com.example.trendyol.data.datasource

import com.example.trendyol.data.api.TYWidgetApi
import com.example.trendyol.model.WidgetResponse
import com.example.trendyol.network.util.safeApiCall
import com.turkcell.android.network.base.NetworkResult
import javax.inject.Inject

class WidgetDataSource
@Inject constructor(
    private val TYWidgetApi: TYWidgetApi
) {
    suspend fun getWidgets(): NetworkResult<WidgetResponse> {
        return safeApiCall {
            TYWidgetApi.getWidgets("interview", "android")
        }
    }
}