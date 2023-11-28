package com.example.giphyapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.giphyapp.app.App
import com.example.giphyapp.databinding.ActivityMainBinding
import com.example.giphyapp.domain.model.response.Data
import com.example.giphyapp.domain.model.response.SearchResponseModel
import com.example.giphyapp.presentation.adapter.GifAdapter
import com.example.giphyapp.presentation.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

class MainActivity() : AppCompatActivity() {
	@Inject
	lateinit var viewModel: MainActivityViewModel

	private lateinit var binding: ActivityMainBinding

	private lateinit var gifAdapter: GifAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(applicationContext as App).applicationComponent.inject(this)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setupRecyclerView()
		setupSearchListener()

	    lifecycleScope.launch {
			viewModel.searchData("").collect{
				gifAdapter.submitData(it)
			}
		}
	}

	private fun setupRecyclerView() {
		gifAdapter = GifAdapter()
		binding.gifRv.adapter = gifAdapter
	}

	private fun setupSearchListener() {
		binding.searchView.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
			override fun afterTextChanged(p0: Editable?) {
				if (p0!!.trim().isNotEmpty()) {
					lifecycleScope.launch {
						// Delay for 5 seconds
						delay(3000)

						viewModel.searchData(p0.toString()).collect {
							gifAdapter.submitData(it)
						}
					}
				}
			}
		})
	}
}