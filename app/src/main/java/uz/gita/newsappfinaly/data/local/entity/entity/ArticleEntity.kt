package uz.gita.newsappfinaly.data.local.entity.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.newsappfinaly.data.model.Article
import uz.gita.newsappfinaly.data.model.Source


@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val articleId: Int,
    @Embedded
    val article: Article
)