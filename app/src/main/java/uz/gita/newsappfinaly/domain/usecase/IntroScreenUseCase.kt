package uz.gita.newsappfinaly.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.IntroData

interface IntroScreenUseCase {

    fun getIntroData(): Flow<List<IntroData>>

    suspend fun setIsFirstIntro(state: Boolean)
}