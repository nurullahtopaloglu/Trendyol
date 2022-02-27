package com.example.trendyol.ui

import androidx.lifecycle.*
import com.example.trendyol.domain.usecase.GetProductUseCase
import com.example.trendyol.domain.usecase.GetWidgetUseCase
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.WidgetResponse
import com.example.trendyol.model.enum.WidgetTypeEnum
import com.turkcell.android.network.base.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val getWidgetUseCase: GetWidgetUseCase,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    // View States
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _widgetResponse = MutableLiveData<WidgetResponse>()
    val widgetResponse: LiveData<WidgetResponse> = _widgetResponse

    private val _productList = MutableLiveData<Pair<ArrayList<ProductResponse>, Int>>()
    val productList: LiveData<Pair<ArrayList<ProductResponse>, Int>> = _productList

    var selectedProduct: ProductResponse? = null

    fun getWidgets() {
        viewModelScope.launch {
            getWidgetUseCase.invoke().collect {
                when (it) {
                    is NetworkResult.Success -> dismissLoading { handleWidgetResponse(it.data!!) }
                    is NetworkResult.Loading -> showLoading()
                    is NetworkResult.Error -> dismissLoading { _error.value = true }
                }
            }
        }
    }

    fun getProducts(index: Int) {
        viewModelScope.launch {
            getProductUseCase.invoke().collect {
                when (it) {
                    is NetworkResult.Success -> dismissLoading { handleProductResponse(it.data!!, index) }
                    is NetworkResult.Loading -> showLoading()
                    is NetworkResult.Error -> dismissLoading { _error.value = true }
                }
            }
        }
    }

    private fun handleWidgetResponse(widgetResponse: WidgetResponse) {
        _widgetResponse.value = widgetResponse
        widgetResponse.widgets.forEachIndexed { index, widget ->
           when (widget.getWidgetType()) {
               WidgetTypeEnum.PRODUCT_SLIDER.value,
               WidgetTypeEnum.PRODUCT_LISTING.value -> getProducts(index)
               else -> {}
           }
        }
    }

    private fun handleProductResponse(productList: ArrayList<ProductResponse>, index: Int) {
        _productList.value = Pair(productList, index)
    }

    fun dismissLoading(call: () -> Unit) {
        _loading.value = false
        call.invoke()
    }

    fun showLoading() {
        _loading.value = true
    }
}
