package com.example.giphyapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphyapp.domain.model.response.Data
import com.example.giphyapp.domain.repository.Repository

class PagingDataSource(
	repository: Repository,
	private val question: String
) : PagingSource<Int, Data>() {

	private val apiService = repository.getApiService()

	override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
		return state.anchorPosition
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
		return try {
			val nextItemIndex = params.key ?: 1
			Log.d("@@@", "${question}")
			val response = apiService.searchGifs(
				apiKey = "YSqXT8r3wui4S7DK8mkXe7mSnhFJZvcg",
				query = question,
				limit = 5,
				offset = nextItemIndex,
				rating = "",
				lang = "en",
				randomId = "e826c9fc5c929e0d6c6d423841a282aa",
				bundle = "messaging_non_clips"
			)

			if (response.isSuccessful) {
				val searchData = response.body()

				// Extract the data directly from the response if it's not a list
				val data = searchData?.data ?: emptyList()
				Log.d("@@@", "${data}")

				LoadResult.Page(
					data = data,
					prevKey = if (nextItemIndex == 1) null else nextItemIndex - 5,
					nextKey = if (data.isEmpty()) null else nextItemIndex + 5
				)
			} else {
				LoadResult.Error(Exception("Failed to load data"))
			}
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
}
