package com.example.giphyapp.data.remote.api

import com.example.giphyapp.domain.model.response.SearchResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("gifs/search")
	suspend fun searchGifs(@Query("api_key") apiKey: String,
	                       @Query("q") query: String,
	                       @Query("limit") limit: Int,
	                       @Query("offset") offset: Int,
	                       @Query("rating") rating: String,
	                       @Query("lang") lang: String,
	                       @Query("random_id") randomId: String,
	                       @Query("bundle") bundle: String
	): Response<SearchResponseModel>
}
