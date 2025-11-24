package com.example.space_marines.data

data class Chapter(
    val id: Int,
    val name: String,
    val homeworld: String,
    val primarch: String,
    val specialty: String,
    val iconUrl: String,
    val lore: String
)