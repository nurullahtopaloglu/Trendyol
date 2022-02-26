package com.example.trendyol.ui.pages.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.HolderBannerCarouselBinding
import com.example.trendyol.databinding.HolderBannerSingleBinding
import com.example.trendyol.model.Widgets

class BannerCarouselHolder(private val binding: HolderBannerCarouselBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Widgets) {
        //binding.model = model
        //binding.tvIdValue.text = model.id.toString()
        binding.executePendingBindings()
    }
}