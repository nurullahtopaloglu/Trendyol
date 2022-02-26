package com.example.trendyol.ui.pages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol.databinding.*
import com.example.trendyol.model.Widgets
import com.example.trendyol.model.enum.WidgetTypeEnum
import com.example.trendyol.ui.pages.holder.*
import javax.inject.Inject

class HomeAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        // from mock-ups
        const val BANNER_SINGLE = 0
        const val BANNER_SLIDER = 1
        const val BANNER_CAROUSEL = 2
        const val PRODUCT_SLIDER = 3
        const val PRODUCT_LISTING = 4
    }

    private var widgets: List<Widgets>? = null

    override fun getItemViewType(position: Int): Int {
        return when (widgets?.get(position)?.getWidgetType()) {
            WidgetTypeEnum.BANNER_SINGLE.value -> BANNER_SINGLE
            WidgetTypeEnum.BANNER_SLIDER.value -> BANNER_SLIDER
            WidgetTypeEnum.BANNER_CAROUSEL.value -> BANNER_CAROUSEL
            WidgetTypeEnum.PRODUCT_SLIDER.value -> PRODUCT_SLIDER
            WidgetTypeEnum.PRODUCT_LISTING.value -> PRODUCT_LISTING
            else -> PRODUCT_LISTING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            BANNER_SINGLE -> {
                val binding = HolderBannerSingleBinding.inflate(inflater, parent, false)
                return BannerSingleHolder(binding)
            }
            BANNER_SLIDER -> {
                val binding = HolderBannerSliderBinding.inflate(inflater, parent, false)
                return BannerSliderHolder(binding)
            }
            BANNER_CAROUSEL -> {
                val binding = HolderBannerCarouselBinding.inflate(inflater, parent, false)
                return BannerCarouselHolder(binding)
            }
            PRODUCT_SLIDER -> {
                val binding = HolderProductSliderBinding.inflate(inflater, parent, false)
                return ProductSliderHolder(binding)
            }
            PRODUCT_LISTING -> {
                val binding = HolderProductListingBinding.inflate(inflater, parent, false)
                return ProductListingHolder(binding)
            }
            else -> {
                val binding = HolderProductListingBinding.inflate(inflater, parent, false)
                return ProductListingHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = widgets?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerSingleHolder -> {
                holder.bind(widgets!![position])
            }
            is BannerSliderHolder -> {
                holder.bind(widgets!![position])
            }
            is BannerCarouselHolder -> {
                holder.bind(widgets!![position])
            }
            is ProductSliderHolder -> {
                holder.bind(widgets!![position])
            }
            is ProductListingHolder -> {
                holder.bind(widgets!![position])
            }
        }
    }

    fun setData(list: List<Widgets>) {
        widgets = list
        notifyDataSetChanged()
    }
}