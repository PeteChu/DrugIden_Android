package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugShapeList(

	@field:SerializedName("results")
	val results: List<DrugShapeItem>? = null
)