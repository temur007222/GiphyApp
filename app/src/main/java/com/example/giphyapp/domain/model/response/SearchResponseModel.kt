package com.example.giphyapp.domain.model.response

data class SearchResponseModel(
    val `data`:List<Data>,
    val meta: Meta,
    val pagination: Pagination
)