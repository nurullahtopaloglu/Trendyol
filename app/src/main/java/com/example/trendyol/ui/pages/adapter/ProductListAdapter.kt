package com.example.trendyol.ui.pages.adapter

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.R
import com.example.trendyol.databinding.*
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.model.Widgets
import com.example.trendyol.ui.util.loadImage
import com.example.trendyol.ui.util.setListingCard
import com.example.trendyol.ui.util.setSliderCardConfig
import javax.inject.Inject

class ProductListAdapter @Inject constructor(
    val onClick: (productResponse: ProductResponse) -> Unit?
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private var products: List<ProductResponse>? = null
    private var widgets: Widgets? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductListingBinding.inflate(inflater, parent, false)
        return ProductListViewHolder(binding)
    }

    override fun getItemCount(): Int = products?.size ?: 0

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(products?.get(position)!!, widgets!!)
    }

    fun setData(products: List<ProductResponse>?, widgets: Widgets) {
        this.products = products
        this.widgets = widgets
        notifyDataSetChanged()
    }

    inner class ProductListViewHolder(private val binding: ItemProductListingBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(productResponse: ProductResponse, widgets: Widgets) {
            binding.model = productResponse
            binding.imgItem.loadImage(productResponse.imageUrl)
            binding.cardView.setListingCard(widgets)

            binding.txtTitle.text = buildSpannedString {
                inSpans(binding.txtTitle.typeface, StyleSpan(Typeface.BOLD), ForegroundColorSpan(ContextCompat.getColor(binding.txtTitle.context, R.color.black))) { append(productResponse.brandName) }
                append(" ")
                inSpans(binding.txtTitle.typeface, StyleSpan(Typeface.NORMAL), ForegroundColorSpan(ContextCompat.getColor(binding.txtTitle.context, R.color.black_80))) { append(productResponse.name) }
            }

            binding.txtPrice.text = productResponse.salePrice.toString() + " TL"
            binding.cardView.setOnClickListener { onClick.invoke(productResponse) }
            binding.executePendingBindings()
        }
    }
}