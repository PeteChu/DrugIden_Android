package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugGroupList(

	@field:SerializedName("results")
	val results: List<DrugGroupItem>? = null
)