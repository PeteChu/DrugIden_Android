package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugTypeItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)