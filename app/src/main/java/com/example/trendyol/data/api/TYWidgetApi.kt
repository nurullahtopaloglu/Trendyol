package com.example.trendyol.data.api

import com.example.trendyol.model.WidgetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TYWidgetApi {

    @GET("personalized")
    suspend fun getWidgets(
        @Query("widgetPageName") widgetPageName: String,
        @Query("platform") platform: String) : Response<WidgetResponse>
}