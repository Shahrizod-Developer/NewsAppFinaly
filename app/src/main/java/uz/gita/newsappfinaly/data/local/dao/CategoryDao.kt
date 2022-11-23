package uz.gita.newsappfinaly.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CategoryEntity
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Category

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("Select * From category")
    fun getAllCategory(): Flow<List<CategoryEntity>>

    @Query("Select * from category Where name Like '%' || :search || '%'")
    fun searchCategory(search: String): Flow<List<CategoryEntity>>

    @Query("Delete From category")
    fun deleteAll()
}