package com.example.trendyol.ui.pages.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.HolderBannerSliderBinding
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.pages.adapter.BannerSliderAdapter

class BannerSliderHolder(private val binding: HolderBannerSliderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Widgets) {
        setRvAdapter(model)
        binding.executePendingBindings()
    }

    private fun setRvAdapter(model: Widgets) {
        val sliderAdapter = BannerSliderAdapter()
        binding.rvSlider.adapter = sliderAdapter
        sliderAdapter.setData(model)
    }
}