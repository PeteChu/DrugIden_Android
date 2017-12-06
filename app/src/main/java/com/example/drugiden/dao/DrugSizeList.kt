package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugSizeList(

	@field:SerializedName("results")
	val results: List<DrugSizeItem>? = null
)