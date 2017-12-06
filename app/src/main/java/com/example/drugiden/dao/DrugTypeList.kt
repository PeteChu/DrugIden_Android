package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugTypeList(

	@field:SerializedName("results")
	val results: List<DrugTypeItem>? = null
)