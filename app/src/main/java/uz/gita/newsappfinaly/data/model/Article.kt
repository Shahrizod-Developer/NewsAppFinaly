package uz.gita.newsappfinaly.data.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize
import uz.gita.newsappfinaly.data.local.entity.entity.ArticleEntity
import uz.gita.newsappfinaly.data.local.entity.entity.Query

@Parcelize
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded
    val source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable