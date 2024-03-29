package com.marcelo.nybooks.network.response

import com.marcelo.nybooks.network.model.Books
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksDetailsResponse(
    @Json(name = "title")
    val title: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "description")
    val description: String
) {
    fun getBookModel() = Books(
        title = this.title,
        author = this.author,
        description = this.description
    )
}