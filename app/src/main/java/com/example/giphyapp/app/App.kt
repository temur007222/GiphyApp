package com.example.giphyapp.app

import android.app.Application
import com.example.giphyapp.di.component.ApplicationComponent
import com.example.giphyapp.di.component.DaggerApplicationComponent
import com.example.giphyapp.di.module.NetworkModule

class App : Application(){
	lateinit var applicationComponent: ApplicationComponent

	override fun onCreate() {
		super.onCreate()
		applicationComponent = DaggerApplicationComponent.builder()
			.networkModule(NetworkModule())
			.build()
	}
}