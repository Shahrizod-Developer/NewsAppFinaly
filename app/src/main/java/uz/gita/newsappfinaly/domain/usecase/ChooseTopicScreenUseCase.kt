package uz.gita.newsappfinaly.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CategoryEntity
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Category
import uz.gita.newsappfinaly.utils.ResultData

interface ChooseTopicScreenUseCase {

    fun getAllCategory(): Flow<ResultData<List<Category>>>

    fun searchCategory(search: String): Flow<ResultData<List<Category>>>
}