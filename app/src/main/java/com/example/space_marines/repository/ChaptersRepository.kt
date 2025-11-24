package com.example.space_marines.repository

import com.example.space_marines.data.Chapter
import com.example.space_marines.network.ChaptersApi

class ChaptersRepository(
    private val api: ChaptersApi,
    private val url: String
) {
    suspend fun loadChapters(): List<Chapter> = api.getChapters(url)
}