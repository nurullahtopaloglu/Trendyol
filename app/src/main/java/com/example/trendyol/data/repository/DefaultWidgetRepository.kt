package com.example.trendyol.data.repository

import com.example.trendyol.data.datasource.WidgetDataSource
import com.example.trendyol.domain.repository.WidgetRepository
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.WidgetResponse
import com.example.trendyol.network.util.requestNetwork
import com.turkcell.android.network.base.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultWidgetRepository
@Inject constructor(
    private val widgetDataSource: WidgetDataSource,
) : WidgetRepository {

    override fun getWidgets(): Flow<NetworkResult<WidgetResponse>> {
        return requestNetwork(
            fetch = {
                widgetDataSource.getWidgets()
            }
        )
    }

    override fun getProducts(): Flow<NetworkResult<ArrayList<ProductResponse>>> {
        return requestNetwork(
            fetch = {
                widgetDataSource.getProducts()
            }
        )
    }
}