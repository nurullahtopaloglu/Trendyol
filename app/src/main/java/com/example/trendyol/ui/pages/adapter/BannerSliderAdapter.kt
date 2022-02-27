package com.example.trendyol.ui.pages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.*
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.util.loadImage
import com.example.trendyol.ui.util.setSliderCardConfig
import javax.inject.Inject

class BannerSliderAdapter @Inject constructor() : RecyclerView.Adapter<BannerSliderAdapter.SliderViewHolder>() {

    private var widget: Widgets? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerSliderAdapter.SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerSliderBinding.inflate(inflater, parent, false)
        return SliderViewHolder(binding)
    }

    override fun getItemCount(): Int = widget?.bannerContents?.size ?: 0

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(widget, position)
    }

    fun setData(widget: Widgets) {
        this.widget = widget
        notifyDataSetChanged()
    }

    inner class SliderViewHolder(private val binding: ItemBannerSliderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(widget: Widgets?, position: Int) {
            val model = widget?.bannerContents!![position]
            binding.model = model
            binding.imgSlider.loadImage(model.imageUrl)
            binding.cardView.setSliderCardConfig(widget, position)
            binding.executePendingBindings()
        }
    }
}