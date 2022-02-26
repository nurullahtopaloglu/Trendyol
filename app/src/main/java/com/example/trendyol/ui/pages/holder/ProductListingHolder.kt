package com.example.trendyol.ui.pages.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.HolderBannerSingleBinding
import com.example.trendyol.databinding.HolderProductListingBinding
import com.example.trendyol.model.Widgets

class ProductListingHolder(private val binding: HolderProductListingBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Widgets) {
        //binding.model = model
        //binding.tvIdValue.text = model.id.toString()
        binding.executePendingBindings()
    }
}