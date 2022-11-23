package uz.gita.newsappfinaly.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Country
import uz.gita.newsappfinaly.utils.ResultData

interface CountryScreenUseCase {

    fun getAllCountry(): Flow<ResultData<List<CountryEntity>>>

    fun searchCountry(search: String): Flow<List<CountryEntity>>

    fun getIsFirst(): Flow<Boolean>
}