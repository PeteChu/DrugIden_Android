package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MedSearchResult(
  @field:SerializedName("results")
  val results: List<ResultsItem>? = null
) : Serializable