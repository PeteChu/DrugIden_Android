package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DrugSearchItem(

	@field:SerializedName("regno")
	val regno: String? = null,

	@field:SerializedName("licenseename")
	val licenseename: String? = null,

	@field:SerializedName("dnote")
	val dnote: Any? = null,

	@field:SerializedName("gid")
	val gid: Int? = null,

	@field:SerializedName("distributorname")
	val distributorname: String? = null,

	@field:SerializedName("dimgpath")
	val dimgpath: String? = null,

	@field:SerializedName("dstatusname")
	val dstatusname: String? = null,

	@field:SerializedName("tradenamename")
	val tradenamename: String? = null,

	@field:SerializedName("genericnameweight")
	val genericnameweight: String? = null,

	@field:SerializedName("dshapename")
	val dshapename: String? = null,

	@field:SerializedName("dwide")
	val dwide: Double? = null,

	@field:SerializedName("dgroupname")
	val dgroupname: String? = null,

	@field:SerializedName("dsizename")
	val dsizename: String? = null,

	@field:SerializedName("dlong")
	val dlong: Double? = null,

	@field:SerializedName("manufacturername")
	val manufacturername: String? = null,

	@field:SerializedName("drtypename")
	val drtypename: String? = null,

	@field:SerializedName("genericname")
	val genericname: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("dorder")
	val dorder: Int? = null,

	@field:SerializedName("dtypename")
	val dtypename: String? = null
) : Serializable