package uz.gita.newsappfinaly.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity

@Dao
interface CountryDao : BaseDao<CountryEntity> {

    @Query("Select * From country")
    fun getAllCountry(): Flow<List<CountryEntity>>

    @Query("Select * from country Where name Like '%' || :search || '%'")
    fun searchCountry(search: String): Flow<List<CountryEntity>>

    @Query("Delete From country")
    fun deleteAll()
}