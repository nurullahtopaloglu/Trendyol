package com.example.trendyol.uicomponents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.trendyol.R
import com.example.trendyol.databinding.CustomToolbarBinding

class CustomToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    var binding: CustomToolbarBinding

    var title: String = ""
        set(value) {
            field = value
            setToolbarTitle(value)
        }

    var isBackVisible: Boolean = false
        set(value) {
            field = value
            setBackVisibility(value)
        }

    var isCloseVisible: Boolean = false
        set(value) {
            field = value
            setCloseVisibility(value)
        }

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_toolbar, this, true)
    }

    private fun setToolbarTitle(value: String) {
        binding.toolbarTitle.text = value
    }

    private fun setBackVisibility(isVisible: Boolean) {
        if (isVisible)
            binding.imgBack.visibility = View.VISIBLE
        else
            binding.imgBack.visibility = View.GONE
    }

    private fun setCloseVisibility(isVisible: Boolean) {
        if (isVisible)
            binding.imgClose.visibility = View.VISIBLE
        else
            binding.imgClose.visibility = View.GONE
    }

    fun getBack() : ImageButton {
        return binding.imgBack
    }

    fun getClose() : ImageView {
        return binding.imgClose
    }
}