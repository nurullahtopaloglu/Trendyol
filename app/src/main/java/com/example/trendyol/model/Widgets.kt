package com.example.trendyol.model

data class Widgets (

    val bannerContents : List<BannerContents>,
    val displayType : String,
    val eventKey : String,
    val id : Int,
    val type : String,
    val displayCount : Int,
    val displayOptions : DisplayOptions,
    val startDate : String,
    val endDate : String,
    val marketing : Marketing,
    val width : Int,
    val height : Int,
    val refreshRequired : Boolean
) {
    fun getWidgetType(): String {
        return type + "_" + displayType
    }
}