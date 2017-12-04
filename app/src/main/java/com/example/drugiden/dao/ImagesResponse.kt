package com.example.drugiden.dao

import com.google.gson.annotations.SerializedName

data class ImagesResponse(

	@field:SerializedName("imageList")
	val imageList: List<ImageListItem>? = null
)