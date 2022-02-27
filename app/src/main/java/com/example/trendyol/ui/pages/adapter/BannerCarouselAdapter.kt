package com.example.trendyol.ui.pages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.*
import com.example.trendyol.model.BannerContents
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.util.loadImage
import javax.inject.Inject

class BannerCarouselAdapter @Inject constructor() : RecyclerView.Adapter<BannerCarouselAdapter.CarouselViewHolder>() {

    private var widget: Widgets? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerCarouselAdapter.CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerCarouselBinding.inflate(inflater, parent, false)
        return CarouselViewHolder(binding)
    }

    override fun getItemCount(): Int = widget?.bannerContents?.size ?: 0

    override fun onBindViewHolder(holder: BannerCarouselAdapter.CarouselViewHolder, position: Int) {
        holder.bind(widget?.bannerContents!![position])
    }

    fun setData(widget: Widgets) {
        this.widget = widget
        notifyDataSetChanged()
    }

    inner class CarouselViewHolder(private val binding: ItemBannerCarouselBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: BannerContents) {
            binding.imgCarousel.loadImage(model.imageUrl)
            binding.executePendingBindings()
        }
    }
}