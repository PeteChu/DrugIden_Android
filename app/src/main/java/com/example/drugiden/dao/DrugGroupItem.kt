package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugGroupItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)