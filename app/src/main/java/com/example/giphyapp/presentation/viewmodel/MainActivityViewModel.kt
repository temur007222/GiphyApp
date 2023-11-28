package com.example.giphyapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.giphyapp.domain.model.response.Data
import com.example.giphyapp.domain.repository.Repository
import com.example.giphyapp.data.remote.PagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

	fun searchData(string:String): Flow<PagingData<Data>> {
		if(string!=""){
			return Pager(PagingConfig(10)){ PagingDataSource(repository,question = string) }.flow.cachedIn(viewModelScope)
		}
		else{
			return Pager(PagingConfig(10)){ PagingDataSource(repository,question =  "cheesburger") }.flow.cachedIn(viewModelScope)
		}
	}
}