package uz.gita.newsappfinaly.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.Query
import retrofit2.HttpException
import uz.gita.newsappfinaly.data.model.Article
import uz.gita.newsappfinaly.data.remote.api.NewsApi
import uz.gita.newsappfinaly.utils.API_KEY
import uz.gita.newsappfinaly.utils.PAGE_SIZE
import java.io.IOException

class TopHeadlinesDataSource(
    private val api: NewsApi,
    private val query: HashMap<String, String>
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val position = params.key ?: 1

        query["pageSize="] = PAGE_SIZE.toString()
        query["apiKey="] = API_KEY

        val response = api.getTopHeadlines(query)
        return try {

            LoadResult.Page(
                data = response.body()?.articles!!,
                prevKey = if (position > 1) position - 1 else null,
                nextKey = if (position < response.body()!!.totalResults / PAGE_SIZE) position + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}