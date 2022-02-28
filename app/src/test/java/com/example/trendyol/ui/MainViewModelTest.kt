package com.example.trendyol.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import com.example.trendyol.MainDispatcherRule
import com.example.trendyol.data.repository.FakeWidgetRepositoryTest
import com.example.trendyol.domain.usecase.GetProductUseCase
import com.example.trendyol.domain.usecase.GetWidgetUseCase
import com.example.trendyol.util.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest: ViewModel() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MainViewModel
    private val fakeRepository = FakeWidgetRepositoryTest()

    @Before
    fun setup() {
        viewModel = MainViewModel(
            getWidgetUseCase =  GetWidgetUseCase(fakeRepository),
            getProductUseCase = GetProductUseCase(fakeRepository)
        )
    }

    /* WIDGET TEST */
    @Test
    internal fun `when widget service returns Error, repository should return Error too`() {
        fakeRepository.setShouldReturnNetworkError(true)
        viewModel.getWidgets()
        val errorVal = viewModel.error.getOrAwaitValue()
        Truth.assertThat(errorVal).isTrue()
    }

    @Test
    internal fun `when widget service returns Success, repository should return Success too`() {
        viewModel.getWidgets()
        val widgetResponse = viewModel.widgetResponse.getOrAwaitValue()
        Truth.assertThat(widgetResponse.widgets).isNotNull()
    }

    /* PRODUCT TEST */
    @Test
    internal fun `when product service returns Error, repository should return Error too`() {
        fakeRepository.setShouldReturnNetworkError(true)
        viewModel.getProducts(0)
        val errorVal = viewModel.error.getOrAwaitValue()
        Truth.assertThat(errorVal).isTrue()
    }

    @Test
    internal fun `when product service returns Success, repository should return Success too`() {
        viewModel.getProducts(0)
        val products = viewModel.productList.getOrAwaitValue()
        Truth.assertThat(products.first[0].boutiqueId).isEqualTo(594725)
    }
}