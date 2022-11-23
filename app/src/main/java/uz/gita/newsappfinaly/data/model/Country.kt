package uz.gita.newsappfinaly.data.model

import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity

data class Country(
    val name: String,
    val code: String,
    val emoji: String,
    val unicode: String,
    val image: String
) {
    fun toEntity() = CountryEntity(0, name, code, emoji, unicode, image, 0)
}