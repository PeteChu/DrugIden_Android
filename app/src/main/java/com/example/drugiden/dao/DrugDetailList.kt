package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DrugDetailList(

	@field:SerializedName("results")
	val results: List<DrugDetailItem>? = null
) :Serializable