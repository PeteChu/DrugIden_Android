package com.example.drugiden.manager.http

import com.example.drugiden.dao.ImagesResponse
import com.example.drugiden.dao.MedSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by schecterza on 10/28/2017.
 */
interface ApiService {

  @GET("/drugs/search/")
  fun quickSearch(@Query("input") input: String): Call<MedSearchResult>

  @GET("/drugs/advance/")
  fun advanceSearch(@Query("drtype") drtype: String
                    , @Query("dtype") dtype: String
                    , @Query("manufacturer") manufacturer: String
                    , @Query("license") license: String
                    , @Query("tradename") tradename: String
                    , @Query("dsize") dsize: String
                    , @Query("distributor") distributor: String
                    , @Query("dstatus") dstatus: String
                    , @Query("color1") color1: String
                    , @Query("color2") color2: String
                    , @Query("color3") color3: String
                    , @Query("color4") color4: String
                    , @Query("dshape") dshape: String
                    , @Query("dgroup") dgroup: String
                    , @Query("dshape1") dshape1: String
                    , @Query("dshape2") dshape2: String
                    , @Query("dshape3") dshape3: String
                    , @Query("dshape4") dshape4: String
  ): Call<String>

  @GET("/drugs/dimg")
  fun getDimages(@Query("id") medicineId: String): Call<ImagesResponse>

}