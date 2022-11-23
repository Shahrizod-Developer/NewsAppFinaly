package uz.gita.newsappfinaly.data.repository.article.impl

import androidx.paging.*
import kotlinx.coroutines.flow.*
import uz.gita.newsappfinaly.data.local.dao.ArticlesDao
import uz.gita.newsappfinaly.data.local.shp.MySharedPreference
import uz.gita.newsappfinaly.data.model.Article
import uz.gita.newsappfinaly.data.remote.api.NewsApi
import uz.gita.newsappfinaly.data.remote.paging.EverythingDataSource
import uz.gita.newsappfinaly.data.remote.paging.TopHeadlinesDataSource
import uz.gita.newsappfinaly.data.repository.article.NewsRepository
import uz.gita.newsappfinaly.utils.PAGE_SIZE
import javax.inject.Inject
import kotlin.collections.HashMap

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: ArticlesDao,
) : NewsRepository {

    private val shp = MySharedPreference.getInstance()

    override fun getTopHeadlinesByQuery(hashMap: HashMap<String, String>): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            TopHeadlinesDataSource(newsApi, hashMap)
        }.flow

    override fun getEverythingByQuery(hashMap: HashMap<String, String>): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            EverythingDataSource(newsApi, hashMap)
        }.flow

    override fun getIsFirst(): Flow<Boolean> = flow { emit(shp.isFirst) }

}
