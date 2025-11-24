package com.example.space_marines.network

import com.example.space_marines.data.Chapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ChaptersApi {
    @GET
    suspend fun getChapters(@Url fullUrl: String): List<Chapter>

    companion object {
        fun create(): ChaptersApi {
            return Retrofit.Builder()
                .baseUrl("https://example.com/") // placeholder, ignored when using @Url
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChaptersApi::class.java)
        }
    }
}