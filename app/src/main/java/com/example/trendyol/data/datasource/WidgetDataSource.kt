package com.example.trendyol.data.datasource

import com.example.trendyol.data.api.TrendyolApi
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.WidgetResponse
import com.example.trendyol.network.util.safeApiCall
import com.example.trendyol.ui.util.Constants.PRODUCT_URL
import com.turkcell.android.network.base.NetworkResult
import javax.inject.Inject

class WidgetDataSource
@Inject constructor(
    private val TrendyolApi: TrendyolApi
) {
    suspend fun getWidgets(): NetworkResult<WidgetResponse> {
        return safeApiCall {
            TrendyolApi.getWidgets("interview", "android")
        }
    }

    suspend fun getProducts(): NetworkResult<ArrayList<ProductResponse>> {
        return safeApiCall {
            TrendyolApi.getProducts(PRODUCT_URL)
        }
    }
}