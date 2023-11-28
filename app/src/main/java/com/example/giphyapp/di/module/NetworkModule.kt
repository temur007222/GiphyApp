package com.example.giphyapp.di.module

import com.example.giphyapp.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

	private val baseUrl = "https://api.giphy.com/v1/"

	@Singleton
	@Provides
	fun provideOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.build()
	}

	@Singleton
	@Provides
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()
	}

	@Singleton
	@Provides
	fun provideApiService(retrofit: Retrofit): ApiService {
		return retrofit.create(ApiService::class.java)
	}
}