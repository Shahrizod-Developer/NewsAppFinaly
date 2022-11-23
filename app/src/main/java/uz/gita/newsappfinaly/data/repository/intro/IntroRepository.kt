package uz.gita.newsappfinaly.data.repository.intro

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Category
import uz.gita.newsappfinaly.data.model.IntroData
import uz.gita.newsappfinaly.utils.ResultData

interface IntroRepository {


    fun getAllCountry(): Flow<ResultData<List<CountryEntity>>>

    fun getIsFirst(): Flow<Boolean>

    suspend fun setIsFirst(state: Boolean)

    fun getIsFirstIntro(): Flow<Boolean>

    suspend fun setIsFirstIntro(state: Boolean)

    fun searchCountry(query: String): Flow<List<CountryEntity>>

    fun searchCategory(query: String): Flow<ResultData<List<Category>>>

    fun getAllCategory(): Flow<ResultData<List<Category>>>

    fun getIntroData(): Flow<List<IntroData>>

}