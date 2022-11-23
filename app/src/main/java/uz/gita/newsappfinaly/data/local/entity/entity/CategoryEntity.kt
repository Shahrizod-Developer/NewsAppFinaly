package uz.gita.newsappfinaly.data.local.entity.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.newsappfinaly.data.model.Category

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    @ColumnInfo(name = "is_checking")
    var isChecking: String,
) {
    fun toCategory() = Category(id, name, image, isChecking)
}