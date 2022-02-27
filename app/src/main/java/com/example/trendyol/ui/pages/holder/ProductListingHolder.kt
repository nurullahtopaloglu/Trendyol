package com.example.trendyol.ui.pages.holder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.HolderProductListingBinding
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.pages.adapter.ProductListAdapter

class ProductListingHolder(private val binding: HolderProductListingBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productList: List<ProductResponse>?, widgets: Widgets, onClick: (productResponse: ProductResponse) -> Unit?) {
        binding.rvListing.layoutManager = GridLayoutManager(binding.rvListing.context,widgets.displayCount)
        setRvAdapter(productList, widgets, onClick)
        binding.executePendingBindings()
    }

    private fun setRvAdapter(products: List<ProductResponse>?, widgets: Widgets, onClick: (productResponse: ProductResponse) -> Unit?) {
        val productListAdapter = ProductListAdapter(onClick)
        binding.rvListing.adapter = productListAdapter
        binding.rvListing.setHasFixedSize(true)
        productListAdapter.setData(products, widgets)
    }
}