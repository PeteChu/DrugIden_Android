package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugImageItem(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("filename")
	val filename: String? = null,

	@field:SerializedName("drugid")
	val drugid: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)