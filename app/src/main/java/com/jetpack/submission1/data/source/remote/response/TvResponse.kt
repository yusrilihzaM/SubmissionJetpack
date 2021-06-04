package com.jetpack.submission1.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(

    @field:SerializedName("page")
	val page: Int,

    @field:SerializedName("total_pages")
	val totalPages: Int,

    @field:SerializedName("results")
	val results: List<TvResultsItem>,

    @field:SerializedName("total_results")
	val totalResults: Int
)