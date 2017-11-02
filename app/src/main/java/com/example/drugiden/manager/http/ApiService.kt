package com.example.drugiden.manager.http

import retrofit2.http.GET

/**
 * Created by schecterza on 10/28/2017.
 */
interface ApiService {

    @GET("/")
    fun quickSearch()

}