package com.example.trendyol.ui

import androidx.lifecycle.*
import com.example.trendyol.domain.usecase.GetWidgetUseCase
import com.example.trendyol.model.WidgetResponse
import com.turkcell.android.network.base.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val getWidgetUseCase: GetWidgetUseCase
) : ViewModel() {

    // View States
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _widgetResponse = MutableLiveData<WidgetResponse>()
    val widgetResponse: LiveData<WidgetResponse> = _widgetResponse

    fun getWidgets() {
        viewModelScope.launch {
            getWidgetUseCase.invoke().collect {
                when (it) {
                    is NetworkResult.Success -> handleWidgetResponse(it.data!!)
                    is NetworkResult.Loading -> _loading.value = true
                    is NetworkResult.Error -> _error.value = true
                }
            }
        }
    }

    private fun handleWidgetResponse(widgetResponse: WidgetResponse) {
        _widgetResponse.value = widgetResponse
    }
}
