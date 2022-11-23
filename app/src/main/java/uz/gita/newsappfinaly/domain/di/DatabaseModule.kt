package uz.gita.newsappfinaly.domain.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappfinaly.data.local.dao.ArticlesDao
import uz.gita.newsappfinaly.data.local.dao.CategoryDao
import uz.gita.newsappfinaly.data.local.dao.CountryDao
import uz.gita.newsappfinaly.data.local.database.AppDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "news")
            .build()

    @Provides
    @Singleton
    fun provideNewsDao(database: AppDatabase): ArticlesDao = database.newsDao()

    @Provides
    @Singleton
    fun provideCountryDao(database: AppDatabase): CountryDao = database.countryDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao = database.categoryDao()
}