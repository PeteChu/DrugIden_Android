package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugShapTypeList(

	@field:SerializedName("results")
	val results: List<DrugShapeTypeItem>? = null
)