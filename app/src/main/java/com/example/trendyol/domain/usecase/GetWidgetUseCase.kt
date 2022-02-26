package com.example.trendyol.domain.usecase

import com.example.trendyol.domain.repository.WidgetRepository
import com.example.trendyol.model.WidgetResponse
import com.turkcell.android.network.base.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWidgetUseCase
@Inject constructor(
    private val widgetRepository: WidgetRepository
) {
    suspend fun invoke(): Flow<NetworkResult<WidgetResponse>> {
        return widgetRepository.getWidgets()
    }
}