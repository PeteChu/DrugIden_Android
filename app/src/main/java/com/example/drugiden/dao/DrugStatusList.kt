package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugStatusList(

	@field:SerializedName("results")
	val results: List<DrugStatusItem>? = null
)