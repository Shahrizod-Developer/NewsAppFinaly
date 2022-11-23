package uz.gita.newsappfinaly.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CategoryEntity
import uz.gita.newsappfinaly.data.model.Category
import uz.gita.newsappfinaly.data.repository.intro.IntroRepository
import uz.gita.newsappfinaly.domain.usecase.ChooseTopicScreenUseCase
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class ChooseTopicScreenUseCaseImpl @Inject constructor(
    private val repository: IntroRepository,
) : ChooseTopicScreenUseCase {
    override fun getAllCategory(): Flow<ResultData<List<Category>>> = repository.getAllCategory()

    override fun searchCategory(search: String): Flow<ResultData<List<Category>>> =
        repository.searchCategory(search)
}