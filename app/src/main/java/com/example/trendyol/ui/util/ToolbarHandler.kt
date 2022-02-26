package com.example.trendyol.ui.util

import android.app.Activity
import com.example.trendyol.uicomponents.CustomToolbar

interface ToolbarHandler {

    fun setToolbarView(activity: Activity, toolbar: CustomToolbar) {
        handleBackClick(activity, toolbar)
        handleCloseClick(activity, toolbar)
    }

    fun handleBackClick(activity: Activity, toolbar: CustomToolbar) {
        toolbar.binding.imgBack.setOnClickListener {
            activity.onBackPressed()
        }
    }

    fun handleCloseClick(activity: Activity, toolbar: CustomToolbar) {
        toolbar.binding.imgClose.setOnClickListener {
            activity.finish()
        }
    }

    fun setTitle(toolbar: CustomToolbar, value: String) {
        toolbar.title = value
    }

    fun setBackVisibility(toolbar: CustomToolbar, isVisible: Boolean) {
        toolbar.isBackVisible = isVisible
    }

    fun setCloseVisibility(toolbar: CustomToolbar, isVisible: Boolean) {
        toolbar.isCloseVisible = isVisible
    }
}