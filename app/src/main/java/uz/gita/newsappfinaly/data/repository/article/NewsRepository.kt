package uz.gita.newsappfinaly.data.repository.article

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.Article

interface NewsRepository {

    fun getIsFirst(): Flow<Boolean>

    fun getTopHeadlinesByQuery(hashMap: HashMap<String, String>): Flow<PagingData<Article>>

    fun getEverythingByQuery(hashMap: HashMap<String, String>): Flow<PagingData<Article>>


}