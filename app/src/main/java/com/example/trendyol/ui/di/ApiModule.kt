package com.example.trendyol.ui.di

import com.example.trendyol.data.api.TrendyolApi
import com.example.trendyol.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideWidgetApi(retrofitProvider: RetrofitProvider): TrendyolApi =
        retrofitProvider.create(TrendyolApi::class.java)
}