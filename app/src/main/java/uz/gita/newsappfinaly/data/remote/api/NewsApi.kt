package uz.gita.newsappfinaly.data.remote.api

import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.GET
import uz.gita.newsappfinaly.data.remote.response.BaseResponse

interface NewsApi {

    @GET("everything")
    suspend fun getEverything(
        @FieldMap params: HashMap<String, String>
    ): Response<BaseResponse>

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @FieldMap params: HashMap<String, String>
    ): Response<BaseResponse>
}