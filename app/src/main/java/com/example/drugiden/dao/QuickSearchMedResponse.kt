package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QuickSearchMedResponse(

        @field:SerializedName("result")
        val result: List<ResultItem>? = null
) : Serializable