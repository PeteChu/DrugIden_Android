package com.example.drugiden.manager.http

import com.example.drugiden.dao.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by schecterza on 10/28/2017.
 */
interface ApiService {

  @GET("/drugs/search/")
  fun quickSearch(@Query("input") input: String,
                  @Query("token") token: String
  ): Call<DrugSearchList>

  @GET("/drugs/search/advance/")
  fun advanceSearch(@Query("color1") color1: String,
                    @Query("color2") color2: String,
                    @Query("color3") color3: String,
                    @Query("color4") color4: String,
                    @Query("tradename") tradename: String,
                    @Query("manufacturer") manufacturer: String,
                    @Query("genericnamelist") genericnamelist: String,
                    @Query("licensee") licensee: String,
                    @Query("distributor") distributor: String,
                    @Query("dgroup") dgroup: String,
                    @Query("dtype") dtype: String,
                    @Query("drtype") drtype: String,
                    @Query("dshape") dshape: String,
                    @Query("dwide") dwide: String,
                    @Query("dlong") dlong: String,
                    @Query("shapetext") shapetext: String,
                    @Query("shapetext2") shapetext2: String,
                    @Query("shapetext3") shapetext3: String,
                    @Query("shapetext4") shapetext4: String,
                    @Query("shapetext5") shapetext5: String,
                    @Query("shapetext6") shapetext6: String,
                    @Query("dsize") dsize: String,
                    @Query("dstatus") dstatus: String,
                    @Query("token") token: String
  ): Call<DrugSearchList>

  @GET("/drugs/dimg")
  fun getDimages(@Query("id") medicineId: String,
                 @Query("token") token: String
  ): Call<DrugImageList>

  /**
   * Get Advance Search Options
   */

  @GET("/ddl/color")
  fun getMedColor(): Call<DrugColorList>

  @GET("/ddl/dgroup")
  fun getMedGroup(): Call<DrugGroupList>

  @GET("/ddl/dsize")
  fun getDrugSize(): Call<DrugSizeList>

  @GET("/ddl/dshape")
  fun getDrugShape(): Call<DrugShapeList>

  @GET("/ddl/dstatus")
  fun getDrugStatus(): Call<DrugStatusList>

  @GET("/ddl/dtype")
  fun getDrugType(): Call<DrugTypeList>

  @GET("/ddl/drtype")
  fun getDrugRType(): Call<DrugRTypeList>

  @GET("/ddl/shapetype")
  fun getDrugShapeType(): Call<DrugShapTypeList>


}