package uz.gita.newsappfinaly.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.newsappfinaly.data.local.dao.ArticlesDao
import uz.gita.newsappfinaly.data.local.dao.CategoryDao
import uz.gita.newsappfinaly.data.local.dao.CountryDao
import uz.gita.newsappfinaly.data.local.entity.entity.ArticleEntity
import uz.gita.newsappfinaly.data.local.entity.entity.CategoryEntity
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity

@Database(entities = [ArticleEntity::class, CountryEntity::class, CategoryEntity::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): ArticlesDao
    abstract fun countryDao(): CountryDao
    abstract fun categoryDao(): CategoryDao
}