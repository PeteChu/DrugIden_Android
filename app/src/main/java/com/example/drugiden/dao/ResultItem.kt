package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("TradeName")
	val tradeName: String? = null,

	@field:SerializedName("TradeName(TH)")
	val tradeNameTH: String? = null,

	@field:SerializedName("ImgPath")
	val imgPath: String? = null,

	@field:SerializedName("Dorder")
	val dorder: Int? = null,

	@field:SerializedName("Rgtno")
	val rgtno: Int? = null,

	@field:SerializedName("Reg.Number")
	val regNumber: String? = null,

	@field:SerializedName("Manufacturer")
	val manufacturer: String? = null,

	@field:SerializedName("licenseName")
	val licenseName: String? = null,

	@field:SerializedName("distributor")
	val distributor: String? = null
)