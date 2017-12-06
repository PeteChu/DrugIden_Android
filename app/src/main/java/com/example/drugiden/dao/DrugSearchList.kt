package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DrugSearchList(
  @field:SerializedName("results")
  val results: List<DrugSearchItem>? = null
) : Serializable