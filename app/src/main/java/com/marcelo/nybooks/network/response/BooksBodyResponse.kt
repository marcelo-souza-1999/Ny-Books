package com.marcelo.nybooks.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksBodyResponse(
    @Json(name = "results")
    val booksResults: List<BooksResultsResponse>
)