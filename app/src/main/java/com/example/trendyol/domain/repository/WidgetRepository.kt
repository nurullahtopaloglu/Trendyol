package com.example.trendyol.domain.repository

import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.WidgetResponse
import com.turkcell.android.network.base.NetworkResult
import kotlinx.coroutines.flow.Flow

interface WidgetRepository {
    fun getWidgets() : Flow<NetworkResult<WidgetResponse>>
    fun getProducts() : Flow<NetworkResult<ArrayList<ProductResponse>>>
}