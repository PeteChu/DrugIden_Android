package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class DrugImageList(

	@field:SerializedName("results")
	val imageList: List<DrugImageItem>? = null
)