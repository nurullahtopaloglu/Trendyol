package com.example.trendyol.ui.di

import android.content.Context
import com.example.trendyol.network.RetrofitProvider
import com.example.trendyol.ui.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitDomain(@ApplicationContext appContext: Context): RetrofitProvider {
        return RetrofitProvider(appContext, Constants.BASE_URL)
    }
}