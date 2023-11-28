package com.example.giphyapp.domain.repository

import com.example.giphyapp.data.remote.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: ApiService){
	fun getApiService(): ApiService =  apiService
}