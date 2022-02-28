package com.example.trendyol.data.repository

import com.example.trendyol.domain.repository.WidgetRepository
import com.example.trendyol.model.Pagination
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.WidgetResponse
import com.example.trendyol.model.Widgets
import com.example.trendyol.util.ResponseHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.turkcell.android.network.base.NetworkResult
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWidgetRepositoryTest : WidgetRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    val widgets : List<Widgets> = mockk(relaxed = true)
    val pagination : Pagination= mockk(relaxed = true)
    var widgetResponse: WidgetResponse = WidgetResponse(widgets, pagination)

    override fun getWidgets(): Flow<NetworkResult<WidgetResponse>> {
       return flow {
           if (shouldReturnNetworkError) {
               emit(NetworkResult.Error("Some Error - Test Scenario"))
           } else {
               emit(NetworkResult.Success(widgetResponse))
           }
       }
    }

    override fun getProducts(): Flow<NetworkResult<ArrayList<ProductResponse>>> {
        return flow {
            if (shouldReturnNetworkError) {
                emit(NetworkResult.Error("Some Error - Test Scenario"))
            } else {
                val typeToken = object : TypeToken<ArrayList<ProductResponse>>() {}.type
                val products = Gson().fromJson<ArrayList<ProductResponse>>(ResponseHelper.productMockJson, typeToken)
                emit(NetworkResult.Success(products))
            }
        }
    }
}