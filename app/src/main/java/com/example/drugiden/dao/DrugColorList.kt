package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName


class DrugColorList(

  @field:SerializedName("results")
  val results: List<DrugColorItem>? = null
)

