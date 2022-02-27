package com.example.trendyol.ui.pages.adapter

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.R
import com.example.trendyol.databinding.*
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.util.loadImage
import com.example.trendyol.ui.util.setSliderCardConfig
import javax.inject.Inject

class ProductSliderAdapter @Inject constructor() : RecyclerView.Adapter<ProductSliderAdapter.SliderViewHolder>() {

    private var products: List<ProductResponse>? = null
    private var widgets: Widgets? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSliderAdapter.SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductSliderBinding.inflate(inflater, parent, false)
        return SliderViewHolder(binding)
    }

    override fun getItemCount(): Int = products?.size ?: 0

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(products?.get(position)!!, widgets!!, position)
    }

    fun setData(products: List<ProductResponse>?, widgets: Widgets) {
        this.products = products
        this.widgets = widgets
        notifyDataSetChanged()
    }

    inner class SliderViewHolder(private val binding: ItemProductSliderBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(productResponse: ProductResponse, widgets: Widgets, position: Int) {
            binding.model = productResponse
            binding.imgItem.loadImage(productResponse.imageUrl)
            binding.cardView.setSliderCardConfig(widgets, position)

            binding.txtTitle.text = buildSpannedString {
                inSpans(binding.txtTitle.typeface, StyleSpan(Typeface.BOLD), ForegroundColorSpan(ContextCompat.getColor(binding.txtTitle.context, R.color.black))) { append(productResponse.brandName) }
                append(" ")
                inSpans(binding.txtTitle.typeface, StyleSpan(Typeface.NORMAL), ForegroundColorSpan(ContextCompat.getColor(binding.txtTitle.context, R.color.black_80))) { append(productResponse.name) }
            }

            binding.txtPrice.text = productResponse.salePrice.toString() + " TL"
            binding.executePendingBindings()
        }
    }
}