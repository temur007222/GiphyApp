package com.example.giphyapp.di.component

import com.example.giphyapp.presentation.ui.MainActivity
import com.example.giphyapp.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		NetworkModule::class
	]
)
interface ApplicationComponent {
	fun inject(mainActivity: MainActivity)
}