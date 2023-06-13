package com.example.domain.details.model

data class AnimeDetails(
    val id:Int,
    val title:String,
    val englishTitle:String,
    val nativeTitle:String,
    val banner:String,
    val episodes:Int?,
    val genre:List<String>?,
    val description:String?,
    val character: List<Character>?,
    val score:Int?
)

data class Character(
    val id: Int,
    val name:String,
    val image:String,
    val description: String?
)