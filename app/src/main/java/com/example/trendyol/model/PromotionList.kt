package com.example.trendyol.model

data class PromotionList (

	val id : String,
	val name : String,
	val shortname : String,
	val type : String,
	val icon : String,
	val colorCode : String,
	val endsIn24Hours : Boolean
)