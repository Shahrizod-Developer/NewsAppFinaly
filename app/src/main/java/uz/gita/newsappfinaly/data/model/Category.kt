package uz.gita.newsappfinaly.data.model

import uz.gita.newsappfinaly.data.local.entity.entity.CategoryEntity
import java.util.UUID
import kotlin.random.Random

data class Category(
    val id: Int,
    val name: String,
    val image: String,
    var isChecking: String,
) {
    fun toCategoryEntity() = CategoryEntity(id, name, image, isChecking)
}