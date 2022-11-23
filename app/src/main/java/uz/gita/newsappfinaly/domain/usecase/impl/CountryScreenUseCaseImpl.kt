package uz.gita.newsappfinaly.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.repository.article.NewsRepository
import uz.gita.newsappfinaly.data.repository.intro.IntroRepository
import uz.gita.newsappfinaly.domain.usecase.CountryScreenUseCase
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class CountryScreenUseCaseImpl @Inject constructor(
    private val repository: IntroRepository
) : CountryScreenUseCase {

    override fun getAllCountry(): Flow<ResultData<List<CountryEntity>>> = repository.getAllCountry()

    override fun searchCountry(search: String): Flow<List<CountryEntity>> =
        repository.searchCountry(search)

    override fun getIsFirst(): Flow<Boolean> = repository.getIsFirst()

}