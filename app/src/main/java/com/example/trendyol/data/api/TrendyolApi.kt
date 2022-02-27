package com.example.trendyol.data.api

import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.WidgetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface TrendyolApi {

    @GET("personalized")
    suspend fun getWidgets(
        @Query("widgetPageName") widgetPageName: String,
        @Query("platform") platform: String) : Response<WidgetResponse>

    @GET()
    suspend fun getProducts(@Url url: String) : Response<ArrayList<ProductResponse>>
}