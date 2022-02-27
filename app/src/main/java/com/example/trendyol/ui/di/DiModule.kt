package com.example.trendyol.ui.di

import com.example.trendyol.data.datasource.WidgetDataSource
import com.example.trendyol.data.repository.DefaultWidgetRepository
import com.example.trendyol.domain.repository.WidgetRepository
import com.example.trendyol.domain.usecase.GetProductUseCase
import com.example.trendyol.domain.usecase.GetWidgetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Singleton
    @Provides
    fun provideGetWidgetUseCase(
        widgetRepository: WidgetRepository
    ): GetWidgetUseCase {
        return GetWidgetUseCase(widgetRepository)
    }

    @Singleton
    @Provides
    fun provideGetProductUseCase(
        widgetRepository: WidgetRepository
    ): GetProductUseCase {
        return GetProductUseCase(widgetRepository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class InterfaceInjector {

    @Singleton
    @Provides
    fun provideWidgetRepository(widgetDataSource: WidgetDataSource): WidgetRepository {
        return DefaultWidgetRepository(widgetDataSource)
    }
}