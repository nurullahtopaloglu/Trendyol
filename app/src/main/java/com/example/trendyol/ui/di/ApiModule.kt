package com.example.trendyol.ui.di

import com.example.trendyol.data.api.TYWidgetApi
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
    fun provideWidgetApi(retrofitProvider: RetrofitProvider): TYWidgetApi =
        retrofitProvider.create(TYWidgetApi::class.java)
}