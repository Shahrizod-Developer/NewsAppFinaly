package uz.gita.newsappfinaly.data.local.entity.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var code: String,
    val emoji: String,
    val unicode: String,
    val image: String,
    var state: Int
)