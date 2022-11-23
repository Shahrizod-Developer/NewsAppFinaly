package uz.gita.newsappfinaly.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SplashScreenUseCase {

    fun getIsFirst(): Flow<Boolean>

    fun getIsFirstIntro(): Flow<Boolean>
}