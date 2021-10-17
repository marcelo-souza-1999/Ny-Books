package com.marcelo.nybooks.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResultsResponse(
    @Json(name = "book_details")
    val bookDetails: List<BooksDetailsResponse>
)