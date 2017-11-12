package com.example.drugiden.manager.http

import com.example.drugiden.dao.QuickSearchMedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by schecterza on 10/28/2017.
 */
interface ApiService {

    @GET("/drugs/search/")
    fun quickSearch(@Query("input") input: String): Call<QuickSearchMedResponse>

}