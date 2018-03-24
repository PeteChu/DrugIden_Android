package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DrugDetailItem(

	@field:SerializedName("phar_cat_nlemname")
	val pharCatNlemname: String? = null,

	@field:SerializedName("gid")
	val gid: Int? = null,

	@field:SerializedName("dstatusname")
	val dstatusname: String? = null,

	@field:SerializedName("indicationname")
	val indicationname: String? = null,

	@field:SerializedName("r_mimsname")
	val rMimsname: String? = null,

	@field:SerializedName("genericnameweight")
	val genericnameweight: String? = null,

	@field:SerializedName("dshapename")
	val dshapename: String? = null,

	@field:SerializedName("dwide")
	val dwide: Double? = null,

	@field:SerializedName("r_medscape")
	val rMedscape: String? = null,

	@field:SerializedName("dlong")
	val dlong: Double? = null,

	@field:SerializedName("phar_cat_uptodatename")
	val pharCatUptodatename: String? = null,

	@field:SerializedName("manufacturername")
	val manufacturername: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("dorder")
	val dorder: Int? = null,

	@field:SerializedName("dtypename")
	val dtypename: String? = null,

	@field:SerializedName("nlemname")
	val nlemname: String? = null,

	@field:SerializedName("regno")
	val regno: String? = null,

	@field:SerializedName("licenseename")
	val licenseename: String? = null,

	@field:SerializedName("dnote")
	val dnote: String? = null,

	@field:SerializedName("distributorname")
	val distributorname: String? = null,

	@field:SerializedName("tradenamename")
	val tradenamename: String? = null,

	@field:SerializedName("dgroupname")
	val dgroupname: String? = null,

	@field:SerializedName("dsizename")
	val dsizename: String? = null,

	@field:SerializedName("r_micromedexname")
	val rMicromedexname: String? = null,

	@field:SerializedName("drtypename")
	val drtypename: String? = null,

	@field:SerializedName("genericname")
	val genericname: String? = null,

	@field:SerializedName("nlem_conditionname")
	val nlemConditionname: String? = null,

	@field:SerializedName("categoryname")
	val categoryname: String? = null
) : Serializable