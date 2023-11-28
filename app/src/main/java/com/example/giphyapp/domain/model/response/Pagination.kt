package com.example.giphyapp.domain.model.response

data class Pagination(
    val count: Int,
    val offset: Int,
    val total_count: Int
)