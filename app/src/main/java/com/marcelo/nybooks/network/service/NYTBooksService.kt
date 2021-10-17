package com.marcelo.nybooks.network.service

import com.marcelo.nybooks.BuildConfig
import com.marcelo.nybooks.network.response.BooksBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTBooksService {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = BuildConfig.KEY_BOOKS_API,
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BooksBodyResponse?>
}