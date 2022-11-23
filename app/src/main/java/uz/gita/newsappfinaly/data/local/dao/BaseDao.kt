package uz.gita.newsappfinaly.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import uz.gita.newsappfinaly.data.local.entity.entity.ArticleEntity

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<T>)

    @Insert
    fun insert(data: T)

    @Update
    fun update(data: T)

    @Update
    fun update(data: List<T>)

    @Delete
    fun delete(data: T)
}