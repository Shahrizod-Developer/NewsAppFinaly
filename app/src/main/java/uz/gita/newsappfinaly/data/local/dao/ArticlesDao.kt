package uz.gita.newsappfinaly.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.ArticleEntity
import uz.gita.newsappfinaly.data.model.Article

@Dao
interface ArticlesDao : BaseDao<ArticleEntity> {

    @Query("SELECT * FROM articles WHERE title=:title")
    fun check(title: String): ArticleEntity?

    @Query("SELECT * FROM articles")
    fun bookmarks(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE title LIKE '%'|| :search ||'%'")
    fun searchBookmark(search: String): Flow<List<ArticleEntity>>
}