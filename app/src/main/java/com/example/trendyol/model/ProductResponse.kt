package com.example.trendyol.model

data class ProductResponse (

	val boutiqueId : Int,
	val brandName : String,
	val brandId : Int,
	val businessUnit : String,
	val businessUnitData : BusinessUnitData,
	val categoryId : Int,
	val categoryHierarchy : String,
	val categoryName : String,
	val contentId : Int,
	val id : Int,
	val imageUrl : String,
	val imageUrls : List<String>,
	val mainId : Int,
	val marketPrice : Double,
	val name : String,
	val promotions : List<String>,
	val salePrice : Double,
	val mOriginalPrice : Double,
	val stockStatus : Int,
	val hasScheduledDelivery : Boolean,
	val discountedPriceInfo : String,
	val promotionList : List<PromotionList>,
	val promotionMessage : String,
	val merchantId : Int,
	val averageRating : Double,
	val ratingCount : Int,
	val discountedPrice : Double,
	val stamps : List<Stamps>,
	val variants : List<Variants>,
	val isDirectCartAdditionAvailable : Boolean,
	val marketing : Marketing,
	val isGroupColorOptionsActive : Boolean,
	val shouldDisableBuyingForDigitalGoods : Boolean
)