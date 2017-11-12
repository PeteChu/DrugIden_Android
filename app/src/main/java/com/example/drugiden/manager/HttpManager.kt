package com.example.drugiden.manager

import com.example.drugiden.manager.http.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by schecterza on 10/28/2017.
 */

class HttpManager {

    var apiService: ApiService

    constructor() {
        var retrofit = Retrofit.Builder()
                .baseUrl("http://159.89.196.187:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getService(): ApiService {
        return apiService
    }

    companion object {
        fun getInstance(): HttpManager {
            var instance = HttpManager()
            return instance
        }
    }

}