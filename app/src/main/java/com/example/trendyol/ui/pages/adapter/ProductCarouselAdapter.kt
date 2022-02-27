package com.example.trendyol.ui.pages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.*
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.util.loadImage
import javax.inject.Inject

class ProductCarouselAdapter @Inject constructor() : RecyclerView.Adapter<ProductCarouselAdapter.CarouselViewHolder>() {

    private var product: ProductResponse? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCarouselAdapter.CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerCarouselBinding.inflate(inflater, parent, false)
        return CarouselViewHolder(binding)
    }

    override fun getItemCount(): Int = product?.imageUrls?.size ?: 0

    override fun onBindViewHolder(holder: ProductCarouselAdapter.CarouselViewHolder, position: Int) {
        holder.bind(product?.imageUrls!![position])
    }

    fun setData(product: ProductResponse) {
        this.product = product
        notifyDataSetChanged()
    }

    inner class CarouselViewHolder(private val binding: ItemBannerCarouselBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(url: String) {
            binding.imgCarousel.loadImage(url)
            binding.executePendingBindings()
        }
    }
}