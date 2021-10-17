package com.marcelo.nybooks.network.service

import com.marcelo.nybooks.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private fun startRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: NYTBooksService = startRetrofit().create(NYTBooksService::class.java)
}