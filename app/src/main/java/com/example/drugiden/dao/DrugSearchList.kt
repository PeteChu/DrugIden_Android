package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DrugSearchList(

    @field:SerializedName("oofsetEnd")
    val oofsetEnd: Int? = null,

    @field:SerializedName("length")
    val length: Int? = null,

    @field:SerializedName("offsetStart")
    val offsetStart: Int? = null,

    @field:SerializedName("results")
    val results: List<DrugSearchItem>? = null
) : Serializable