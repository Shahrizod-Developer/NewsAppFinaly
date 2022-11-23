package uz.gita.newsappfinaly.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappfinaly.data.repository.article.NewsRepository
import uz.gita.newsappfinaly.data.repository.article.impl.NewsRepositoryImpl
import uz.gita.newsappfinaly.data.repository.auth.AuthRepository
import uz.gita.newsappfinaly.data.repository.auth.impl.AuthRepositoryImpl
import uz.gita.newsappfinaly.data.repository.intro.IntroRepository
import uz.gita.newsappfinaly.data.repository.intro.impl.IntroRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    @Binds
    fun bindIntroRepository(impl: IntroRepositoryImpl): IntroRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}