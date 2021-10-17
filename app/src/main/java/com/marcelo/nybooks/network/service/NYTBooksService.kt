package com.marcelo.nybooks.network.service

import retrofit2.Call
import retrofit2.http.GET

interface NYTBooksService {

    @GET("lists.json")
    fun listRepos(): Call<List<?>>

}