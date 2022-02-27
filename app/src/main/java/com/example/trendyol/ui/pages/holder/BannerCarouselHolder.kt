package com.example.trendyol.ui.pages.holder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.trendyol.databinding.HolderBannerCarouselBinding
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.pages.adapter.BannerCarouselAdapter
import com.example.trendyol.ui.util.setCardConfig

class BannerCarouselHolder(private val binding: HolderBannerCarouselBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Widgets) {
        binding.cardView.setCardConfig(model)
        setVpAdapter(model)
        binding.executePendingBindings()
    }

    private fun setVpAdapter(model: Widgets) {
        val carouselAdapter = BannerCarouselAdapter()
        binding.vpBanner.adapter = carouselAdapter
        carouselAdapter.setData(model)

        binding.txtVpState.text = "1/" + model.bannerContents.size
        binding.vpBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.txtVpState.text = (position + 1).toString() + "/" + model.bannerContents.size
            }
        })
    }
}