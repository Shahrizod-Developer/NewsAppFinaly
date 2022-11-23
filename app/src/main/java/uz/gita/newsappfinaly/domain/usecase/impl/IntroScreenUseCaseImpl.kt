package uz.gita.newsappfinaly.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.IntroData
import uz.gita.newsappfinaly.data.repository.intro.IntroRepository
import uz.gita.newsappfinaly.domain.usecase.IntroScreenUseCase
import javax.inject.Inject

class IntroScreenUseCaseImpl @Inject constructor(
    private val repository: IntroRepository,
) : IntroScreenUseCase {

    override fun getIntroData(): Flow<List<IntroData>> = repository.getIntroData()

    override suspend fun setIsFirstIntro(state: Boolean) = repository.setIsFirstIntro(state)
}