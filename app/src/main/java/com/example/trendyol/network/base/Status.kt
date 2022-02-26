package com.turkcell.android.network.base

data class Status (
	val httpStatusCode : Int,
	val resultCode : Int,
	val resultMessage : String
)