package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugRTypeList(

	@field:SerializedName("results")
	val results: List<DrugRTypeItem>? = null
)