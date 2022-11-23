package uz.gita.newsappfinaly.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.repository.article.NewsRepository
import uz.gita.newsappfinaly.data.repository.intro.IntroRepository
import uz.gita.newsappfinaly.domain.usecase.SplashScreenUseCase
import javax.inject.Inject

class SplashScreenUseCaseImpl @Inject constructor(
    private val repository: IntroRepository,
) : SplashScreenUseCase {

    override fun getIsFirst(): Flow<Boolean> = repository.getIsFirst()

    override fun getIsFirstIntro(): Flow<Boolean> = repository.getIsFirstIntro()
}