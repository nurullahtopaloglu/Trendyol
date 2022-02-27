package com.example.trendyol.ui.pages.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.HolderBannerSingleBinding
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.util.loadImage
import com.example.trendyol.ui.util.setCardConfig

class BannerSingleHolder(private val binding: HolderBannerSingleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Widgets) {
        binding.model = model
        binding.imgBanner.loadImage(model.bannerContents[0].imageUrl)
        binding.cardView.setCardConfig(model)
        binding.executePendingBindings()
    }
}