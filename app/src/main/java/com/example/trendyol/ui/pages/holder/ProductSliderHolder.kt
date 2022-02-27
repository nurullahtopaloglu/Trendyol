package com.example.trendyol.ui.pages.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.HolderProductSliderBinding
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.pages.adapter.BannerSliderAdapter
import com.example.trendyol.ui.pages.adapter.ProductSliderAdapter

class ProductSliderHolder(private val binding: HolderProductSliderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productList: List<ProductResponse>?, widgets: Widgets) {
        setRvAdapter(productList, widgets)
        binding.executePendingBindings()
    }

    private fun setRvAdapter(products: List<ProductResponse>?, widgets: Widgets) {
        val sliderAdapter = ProductSliderAdapter()
        binding.rvSlider.adapter = sliderAdapter
        sliderAdapter.setData(products, widgets)
    }
}