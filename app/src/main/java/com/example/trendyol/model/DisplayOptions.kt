package com.example.trendyol.model

data class DisplayOptions (

	val showProductPrice : Boolean,
	val showProductFavoredButton : Boolean,
	val showClearButton : Boolean,
	val showCountdown : Boolean,
	val showCountOnTitle : Boolean,
	val paddingTopBottom : Int = 0,
	val paddingRightLeft : Int = 0
)